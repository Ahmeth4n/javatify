package com.magnakod.emulator;

import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.base.request.EnvironmentSettingsOkhttpBuilder;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginResponseAfterRegister;
import com.magnakod.emulator.requests.RegisterLoginRequest;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.entity.Accounts;
import com.magnakod.repository.AccountsRepository;
import com.magnakod.repository.SettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Spotify {
    @Autowired
    private SettingsRepository settingsRepository;
    private final Logger logger = LoggerFactory.getLogger(Spotify.class);

    private EnvironmentSettingsOkhttpBuilder environmentSettingsOkhttpBuilder;
    public EnvironmentSettingsOkhttpBuilder getProxy(){
        if(environmentSettingsOkhttpBuilder == null){
            environmentSettingsOkhttpBuilder = new EnvironmentSettingsOkhttpBuilder();
            logger.info("getProxy() - new okhttp environment settings created.");
        }

        String proxyList = settingsRepository.findAll().get(0).getProxyList();
        String[] proxiesArr = proxyList.split("\r\n");
        int rnd = new Random().nextInt(proxiesArr.length);
        String[] proxyInformations = proxiesArr[rnd].split(":");

        String proxyUsername = proxyInformations[0];
        String proxyPassword = proxyInformations[1];
        String proxyHost = proxyInformations[2];
        int proxyPort = Integer.parseInt(proxyInformations[3]);

        logger.info("getProxy() - selected proxy {}:{}",proxyHost,proxyPort);

        environmentSettingsOkhttpBuilder.setProxy(EnvironmentSettingsOkhttpBuilder.ProxyType.PROXY_SERVICE,proxyHost,proxyPort,proxyUsername,proxyPassword);
        return environmentSettingsOkhttpBuilder;
    }
    private List<SpotifySessionModel> sessionMap;
    private final AtomicInteger loadedSessionCount = new AtomicInteger(0);
    private final AtomicInteger savedCollectionCount = new AtomicInteger(0);
    public Spotify(){
        createNewSession();
    }
    public void createNewSession(){
        sessionMap = new ArrayList<>();
    }
    public void loadSession(SpotifySessionModel model){
        sessionMap.add(model);
        loadedSessionCount.getAndIncrement();
    }
    public int getSessionCount(){
        return loadedSessionCount.get();
    }
    public int getSavedCollectionCount(){
        return savedCollectionCount.get();
    }
    public List<SpotifySessionModel> getSession(){
        return sessionMap;
    }
    public List<SpotifySessionModel> updateSession(SpotifySession session,SpotifyHeaders headers, boolean proxyStatus) throws Exception {
        BaseRequest baseRequest = null;
        if (proxyStatus){
            EnvironmentSettingsOkhttpBuilder environmentSettingsOkhttpBuilder = getProxy();
            baseRequest = BaseRequest.getInstance(environmentSettingsOkhttpBuilder);
        }else{
            baseRequest = BaseRequest.getInstance(null);
        }

        RegisterLoginRequest registerLoginRequest = new RegisterLoginRequest(session,headers, baseRequest);
        List<SpotifySessionModel> updatedHeaders = new ArrayList<>();
        byte[] updatedSessions = registerLoginRequest.login_after_register_last();
        logger.info("{} updateSession - returned: {}",session.getGeneratedUsername(), Base64.getEncoder().encodeToString(updatedSessions));
        if (updatedSessions == null){
            logger.error("{} - session renew request failed. response is NULL",session.getEmailAddress());
            return null;
        }
        SpLoginResponseAfterRegister.LoginMessage registerLoginMessageLast = SpLoginResponseAfterRegister.SPLoginResponseAfterRegister.parseFrom(updatedSessions).getLoginMessage();
        session.setAuthorizationToken(registerLoginMessageLast.getAuthorizationToken());
        session.setGeneratedUsername(registerLoginMessageLast.getUsername());
        headers.setTemporaryClientToken(registerLoginMessageLast.getClientToken());

        logger.info("{} - session updated. new authentication token is: {}",session.getEmailAddress(),session.getAuthorizationToken());

        SpotifySessionModel spotifySessionModel = new SpotifySessionModel(session,headers);
        updatedHeaders.add(spotifySessionModel);

        return updatedHeaders;
    }
}
