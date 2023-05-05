package com.magnakod.executor;

import com.magnakod.Constants;
import com.magnakod.emulator.Spotify;
import com.magnakod.emulator.SpotifySessionModel;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.base.request.EnvironmentSettingsOkhttpBuilder;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.follow.SpotifyCollections;
import com.magnakod.emulator.playlist.SpotifyPlaylist;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import com.magnakod.entity.Accounts;
import com.magnakod.entity.Tasks;
import com.magnakod.service.accounts.AccountsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Async
public class LocalTaskExecutor extends Spotify {
    private final SpotifyUtils utils;
    private final BaseRequest baseRequest;
    private final Logger logger = LoggerFactory.getLogger(LocalTaskExecutor.class);
    private final AccountsServiceImpl accountsService;
    private final Tasks tasks;
    public LocalTaskExecutor(Tasks tasks,AccountsServiceImpl accountsService) {
        this.utils = new SpotifyUtils();
        this.accountsService = accountsService;
        this.tasks = tasks;

        if (tasks.isTaskProxyStatus()){
            EnvironmentSettingsOkhttpBuilder environmentSettingsOkhttpBuilder = getProxy();
            this.baseRequest = BaseRequest.getInstance(environmentSettingsOkhttpBuilder);
        }else{
            this.baseRequest = BaseRequest.getInstance(null);
        }

    }

    public Accounts getSessions(Accounts accounts) throws Exception {
        List<SpotifySessionModel> sessionModel = updateSession(accounts.getSession(), accounts.getHeaders(),tasks.isTaskProxyStatus() );
        SpotifySession session = sessionModel.get(0).getSession();
        SpotifyHeaders headers = sessionModel.get(0).getHeaders();

        loadSession(new SpotifySessionModel(session, headers));

        // update new session and header creds.
        accountsService.sessionUpdate(accounts,session,headers);

        return accounts;
    }

    public boolean startTask(Accounts accounts, String url, Constants.SPOTIFY_TASK_TYPE taskType) {
        try {
            accounts = getSessions(accounts);
        } catch (Exception e) {
            logger.error("startTask() - exception : {}", e.getMessage());
        }
        try {
            if (taskType == Constants.SPOTIFY_TASK_TYPE.SPOTIFY_PLAYLIST_SAVE) {
                SpotifyPlaylist spotifyPlaylist = new SpotifyPlaylist(accounts.getHeaders(), accounts.getSession(), baseRequest);
                String followPlaylist = spotifyPlaylist.followPlaylist(url);

                if (followPlaylist != null) {
                    return true;
                }

            } else {
                SpotifyCollections spotifyCollections = new SpotifyCollections(accounts.getHeaders(), utils, accounts.getSession(), baseRequest);
                logger.info("{} - loaded session for {}", accounts.getSession().getEmailAddress(), taskType);
                byte[] response = spotifyCollections.collectionRequest(url, taskType);

                if (response != null) {
                    return true;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
