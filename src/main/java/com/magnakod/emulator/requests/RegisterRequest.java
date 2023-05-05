package com.magnakod.emulator.requests;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpRegisterResponse;
import com.magnakod.emulator.proto.objects.GetLoginMessage;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import okhttp3.Headers;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;

public class RegisterRequest extends SpotifyBase {
    private final BaseRequest request;
    private final SpotifyConstants consts;
    private final SpotifyHeaders headers;
    private final SpotifyUtils utils;
    private final SpotifySession session;
    public RegisterRequest(SpotifyHeaders headers, SpotifyConstants constants, SpotifyUtils utils, SpotifySession session, BaseRequest request) {
        super(headers, constants, utils, session, request);
        this.request = request;
        this.headers = headers;
        this.consts = constants;
        this.session = session;
        this.utils = utils;
    }

    public Response create_account(String device_id) throws Exception {
        GetLoginMessage payload = new GetLoginMessage(utils,session,consts);
        String encoded_register = payload.register_payload(device_id);

        String url = "https://spclient.wg.spotify.com/signup/public/v2/account/create";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("X-Client-Id",session.getVersion().getClientID());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",consts.SP_OS_TYPE);

        byte[] decoded_post_data = Base64.getDecoder().decode(encoded_register);

        request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        Response response = request.post_request(url,decoded_post_data,headers);
//        register_parser(response);
        return response;
    }

    public Response signup_validate() throws Exception {
        String url = "https://spclient.wg.spotify.com/signup/public/v1/account/?validate=1&key="+consts.SP_SIGN_UP_KEY;
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",consts.SP_OS_TYPE);

        Headers headers = Headers.of(header);
        Response response = this.request.get_request(url,headers);

        return response;
    }

    public Response signup_password(String password) throws Exception {
        String url = "https://spclient.wg.spotify.com/signup/public/v1/account/?validate=1&suggest=1&key="+consts.SP_SIGN_UP_KEY+"&password="+ URLEncoder.encode(password);
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",consts.SP_OS_TYPE);

        Headers headers = Headers.of(header);
        Response response = this.request.get_request(url,headers);

        return response;
    }
    public SpRegisterResponse.RegisterInformation getMessage(Response response) throws IOException {
        SpRegisterResponse.RegisterInformation register_response = SpRegisterResponse.SPRegisterResponse.parseFrom(response.body().bytes()).getRegisterInformation();
        return register_response;
    }
}
