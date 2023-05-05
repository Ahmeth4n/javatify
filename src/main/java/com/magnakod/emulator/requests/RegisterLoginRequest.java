package com.magnakod.emulator.requests;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterFirst;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterLast;
import com.magnakod.emulator.proto.objects.register.RegisterLoginFirst;
import com.magnakod.emulator.proto.objects.register.RegisterLoginLast;
import com.magnakod.emulator.session.SpotifySession;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class RegisterLoginRequest extends SpotifyBase {
    private final SpotifyHeaders headers;
    private final BaseRequest request;
    private final SpotifySession session;
    private final Logger logger = LoggerFactory.getLogger(RegisterLoginRequest.class);

    public RegisterLoginRequest(SpotifySession session, SpotifyHeaders headers, BaseRequest request) {
        super(session, headers, request);
        this.session = session;
        this.headers = headers;
        this.request = request;
    }

    public Response login_after_register() throws Exception {
        RegisterLoginFirst registerLoginFirst = new RegisterLoginFirst(session,headers);

        byte[] post_body = registerLoginFirst.create();

        String s = new String(post_body, StandardCharsets.UTF_8);

        logger.info("login_after_register() - login data: {}", s);

        String url = "https://login5.spotify.com/v3/login";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","gzip, deflate");
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Client-Token",headers.getClientToken());

        this.request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        Response response = this.request.post_request(url,post_body,headers);
        return response;

    }

    public byte[] login_after_register_last() throws Exception {
        RegisterLoginLast registerLoginLast = new RegisterLoginLast(session,headers);
        byte[] post_body = registerLoginLast.create();

        String s = new String(post_body, StandardCharsets.UTF_8);
        logger.info("login_after_register_last() - latest login data: {}",s);

        String url = "https://login5.spotify.com/v3/login";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","gzip, deflate");
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Client-Token",headers.getClientToken());


        request.setContent_type("application/x-protobuf");

        Headers headers = Headers.of(header);
        Response response = request.post_request(url,post_body,headers);
        ResponseBody responseBody = response.body();
        if (responseBody != null){
            byte[] responseBodyBytes = responseBody.bytes();
            logger.info("login_after_register_last() returned: {}" ,Base64.getEncoder().encodeToString(responseBodyBytes));
            return responseBodyBytes;
        }
        return null;
    }

}
