package com.magnakod.emulator.requests;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import okhttp3.Headers;
import okhttp3.Response;

import java.net.URLEncoder;
import java.util.HashMap;

public class AccountSettings extends SpotifyBase {
    private SpotifySession session;
    private SpotifyHeaders headers;
    private BaseRequest request;
    public AccountSettings(SpotifySession session, SpotifyHeaders headers, BaseRequest request) {
        super(session,headers,request);
        this.session = session;
        this.headers = headers;
        this.request = request;
    }

    public String getAccountSettings() throws Exception {
        String url = "https://spclient.wg.spotify.com/accountsettings/v1/profile/email";
        HashMap<String,String> header = new HashMap<>();
        header.put("Client-Token",headers.getClientToken());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("X-Client-Id",session.getVersion().getClientID());
        header.put("App-Platform",headers.getAppVersion());
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());
        header.put("Accept","gzip, deflate");

        request.setContent_type("application/octet-stream");
        Headers headers = Headers.of(header);
        Response response = request.get_request(url,headers);
        String responseStr = response.body().string();
        return responseStr;
    }
}
