package com.magnakod.emulator.runner;

import com.magnakod.emulator.Spotify;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.SpotifySessionModel;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.base.request.EnvironmentSettingsOkhttpBuilder;
import com.magnakod.emulator.builder.SpotifyBuilder;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.builder.SpotifyUserBuilder;
import com.magnakod.emulator.client_token.SpotifyClientToken;
import com.magnakod.emulator.proto.objects.login.SpotifyFirstLoginProto;
import com.magnakod.emulator.proto.objects.login.SpotifySecondLoginProto;
import com.magnakod.emulator.proto.objects.register.RegisterProto;
import com.magnakod.emulator.proto.objects.GetArtists;
import com.magnakod.emulator.proto.objects.GetSendArtists;
import com.magnakod.emulator.requests.RegisterLoginRequest;
import com.magnakod.emulator.requests.RegisterRequest;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import com.magnakod.repository.AccountsRepository;
import com.magnakod.service.accounts.AccountsServiceImpl;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class SpotifyRegisterRunner {
 private final Spotify spotify;
 private final AccountsServiceImpl accountsService;
 public SpotifyRegisterRunner(AccountsServiceImpl accountsService, Spotify spotify) {
  this.spotify = spotify;
  this.accountsService = accountsService;
 }
 private final Logger logger = LoggerFactory.getLogger(SpotifyRegisterRunner.class);
 public boolean register(boolean proxyStatus) throws Exception {
     long start = System.currentTimeMillis();

     BaseRequest request = null;
     if (proxyStatus){
         EnvironmentSettingsOkhttpBuilder environmentSettingsOkhttpBuilder = spotify.getProxy();
         request = BaseRequest.getInstance(environmentSettingsOkhttpBuilder);
     }else{
         request = BaseRequest.getInstance(null);
     }

     SpotifyConstants constants = new SpotifyConstants();
     SpotifyUtils utils = new SpotifyUtils();

     SpotifySession session = new SpotifyUserBuilder(utils,constants).build();
     logger.info("register() - session created {} ", session.toString());
     SpotifyHeaders headers = new SpotifyBuilder(constants,request,utils).build();
     logger.info("register() - device creds. created {} ", headers.toString());

     SpotifyClientToken spotifyClientToken = new SpotifyClientToken(session,headers,constants,request);
     String clientToken = spotifyClientToken.get_full_token();
     logger.info("get_full_token() - client token: {}", clientToken);

     RegisterRequest registerRequest = new RegisterRequest(headers,constants,utils,session,request);
     Response validateSignup = registerRequest.signup_validate();
     logger.info("signup_validate() - response: {}", validateSignup.body().string());

     Response signUpPassword = registerRequest.signup_password(session.getPassword());
     String signUpPasswordResp = signUpPassword.body().string();
     logger.info("signUpPassword() - response: {}", signUpPasswordResp);

     Response createAccount = registerRequest.create_account(headers.getDeviceId());
     String createAccountResp = createAccount.body().string();
     String createAccountEncoded = Base64.getEncoder().encodeToString(createAccountResp.getBytes());
     logger.info("create_account() - response: {}",createAccountEncoded);

     RegisterProto registerProto = new RegisterProto(session);
     registerProto.parse(createAccountResp.getBytes());

     RegisterLoginRequest registerLoginRequest = new RegisterLoginRequest(session,headers,request);
     Response loginAfterRegister = registerLoginRequest.login_after_register();

     SpotifyFirstLoginProto firstLoginProto = new SpotifyFirstLoginProto(session,headers);
     firstLoginProto.parse(loginAfterRegister.body().bytes());

     byte[] loginAfterRegisterLast = registerLoginRequest.login_after_register_last();

     SpotifySecondLoginProto spotifySecondLoginProto = new SpotifySecondLoginProto(session,headers);
     spotifySecondLoginProto.parse(loginAfterRegisterLast);

     GetArtists getArtists = new GetArtists(headers,constants,utils,session,request);
     byte[] artists = getArtists.getArtists();
     logger.info("getArtists() - returned: {}", Base64.getEncoder().encodeToString(artists));

     String getSessionId = new String(artists);
     String sessionId = getArtists.getSessionId(getSessionId);
     if (sessionId != null){
         logger.warn("getSessionId() - parsed: {}",sessionId);
     }else{
         logger.error("getSessionId() - sessionId parse error. returned NULL.");
         return false;
     }
     GetSendArtists getSendArtists = new GetSendArtists(session,headers,constants,request,utils);
     String sendArtists = getSendArtists.sendArtist(sessionId);
     String sendArtistEncoded = Base64.getEncoder().encodeToString(sendArtists.getBytes());
     logger.info("sendArtist() - return data (encoded) : {}",sendArtistEncoded);

     accountsService.save(session,headers);

     SpotifySessionModel model = new SpotifySessionModel(session,headers);
     spotify.loadSession(model);

     /*
     String artist_uri = "spotify:artist:4kzJtf1HMdOfQgr0B5vqua";

     SpotifyCollections follow = new SpotifyCollections(headers,utils,session,request);
     byte[] followRequest = follow.collectionRequest(artist_uri, Constants.SPOTIFY_TASK_TYPE.SPOTIFY_ARTIST_FOLLOW);

     if (followRequest == null){
         logger.error("collectionRequest() - got error. response is a NULL");
         return false;
     }

     logger.info("current action is {}",SpotifyConstants.COLLECTION_TYPES.ARTIST);
     logger.info("collectionRequest() - response is: {}", Base64.getEncoder().encodeToString(followRequest));
    */

     long end = System.currentTimeMillis();
     float sec = (end - start) / 1000F;

     logger.info("register() - elapsed time: " + sec + " seconds.");

     return true;
    }
}
