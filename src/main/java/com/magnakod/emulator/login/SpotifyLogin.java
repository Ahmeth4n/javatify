package com.magnakod.emulator.login;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.objects.login.SpotifyLoginProto;
import com.magnakod.emulator.session.SpotifySession;
import okhttp3.Headers;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;

public class SpotifyLogin extends SpotifyBase {
    private final SpotifySession session;
    private final SpotifyHeaders headers;
    private final BaseRequest request;
    private final Logger logger = LoggerFactory.getLogger(SpotifyLogin.class);
    public SpotifyLogin(SpotifyHeaders headers, SpotifySession session, BaseRequest request) {
        super(headers, session);
        this.session = session;
        this.headers = headers;
        this.request = request;
    }
    private byte[] getFirstLogin(byte[] loginMessage) throws Exception {
        String url = "https://login5.spotify.com/v3/login";

        HashMap<String,String> header = new HashMap<>();
        header.put("Client-Token",headers.getClientToken());
        header.put("User-Agent",session.getVersion().getUserAgent());
        request.setContent_type("application/x-protobuf");

        Headers headers = Headers.of(header);
        Response response = request.post_request(url,loginMessage,headers);

        byte[] responseBytes = response.body().bytes();
        return responseBytes;
    }

    public void login() throws Exception {
        logger.info("{} - old temporary client token is: {}",session.getEmailAddress(),headers.getTemporaryClientToken());
        SpotifyLoginProto spotifyLoginProto = new SpotifyLoginProto(session,headers);
        byte[] loginMessage = spotifyLoginProto.create();
        byte[] response = getFirstLogin(loginMessage);
        spotifyLoginProto.parse(response);
        logger.info("{} - new temporary client token is: {}",session.getEmailAddress(),headers.getTemporaryClientToken());
        logger.info("login response is: {}", Base64.getEncoder().encodeToString(response));
    }

}
