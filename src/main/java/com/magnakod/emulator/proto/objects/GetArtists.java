package com.magnakod.emulator.proto.objects;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetArtists extends SpotifyBase {
    private final SpotifyHeaders headers;
    private final SpotifyConstants consts;
    private final BaseRequest request;
    private final SpotifySession session;

    public GetArtists(SpotifyHeaders headers, SpotifyConstants constants, SpotifyUtils utils, SpotifySession session, BaseRequest request) {
        super(headers, constants, utils, session, request);
        this.headers = headers;
        this.consts = constants;
        this.request = request;
        this.session = session;
    }

    public String getSessionId(String response){
        String regex = "session-id=(.*?)&";
        String sessionId = null;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(response);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                sessionId = matcher.group(i);
            }
        }
        return sessionId;
    }
    public byte[] getArtists() throws Exception {
        String url = "https://spclient.wg.spotify.com/allboarding/v1/onboarding/?deeplink=&entry-point=SIGNUP&manufacturer=unknown&model="+ URLEncoder.encode(consts.SP_OS_SDK) +"&platform=android";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("X-Client-Id",session.getVersion().getClientID());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",consts.SP_OS_TYPE);
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());

        request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        Response response = request.get_request(url,headers);
        byte[] responseStr = response.body().bytes();

        return responseStr;
    }
}
