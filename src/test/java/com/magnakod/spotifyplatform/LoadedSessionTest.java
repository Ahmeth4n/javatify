package com.magnakod.spotifyplatform;

import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.SpotifySessionModel;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.client_token.SpotifyClientToken;
import com.magnakod.emulator.playlist.SpotifyPlaylist;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.entity.Accounts;
import com.magnakod.repository.AccountsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.magnakod.SpotifyPlatformApplication.getSpotifyBean;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LoadedSessionTest {
    private final Logger logger = LoggerFactory.getLogger(LoadedSessionTest.class);
    @Autowired
    private AccountsRepository accountsRepository;

    @Test
    public void loadSessionTest(){
        Accounts account = accountsRepository.findAccountsByIdIs("63f4ad0535496f56abce1c01");
        SpotifySession session = account.getSession();
        SpotifyHeaders headers = account.getHeaders();
        String oldAuthToken = session.getAuthorizationToken();
        BaseRequest request = new BaseRequest();
        List<SpotifySessionModel> sessionMap;
        try {
            SpotifyClientToken clientToken = new SpotifyClientToken(session,headers,new SpotifyConstants(),request);
            String clientTokenStr = clientToken.get_full_token();
            logger.info("{} - get new client token: {}",session.getEmailAddress(),clientTokenStr);
            headers.setClientToken(clientTokenStr);

            sessionMap = getSpotifyBean().updateSession(session,headers,false);
            logger.error("{} - new auth token is : {}",sessionMap.get(0).getSession().getGeneratedUsername(),sessionMap.get(0).getSession().getAuthorizationToken());

            SpotifyPlaylist playlist = new SpotifyPlaylist(headers,session,request);
            String playlistProto = playlist.getPlaylistKey();

            logger.info("{} - get playlist key: {}",session.getEmailAddress() ,playlistProto);

            assertNotNull(playlistProto);
            assertNotEquals(oldAuthToken,sessionMap.get(0).getSession().getAuthorizationToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
