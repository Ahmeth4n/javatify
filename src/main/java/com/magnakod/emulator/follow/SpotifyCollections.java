package com.magnakod.emulator.follow;

import com.magnakod.Constants;
import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.objects.collections.SpotifyCollectionsProto;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import okhttp3.Headers;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;

public class SpotifyCollections extends SpotifyBase {
    private final SpotifyHeaders headers;
    private final SpotifySession session;
    private final BaseRequest request;
    private final SpotifyUtils utils;
    private final Logger logger = LoggerFactory.getLogger(SpotifyCollections.class);

    public SpotifyCollections(SpotifyHeaders headers, SpotifyUtils utils, SpotifySession session, BaseRequest request) {
        super(headers,utils,session,request);
        this.headers = headers;
        this.session = session;
        this.utils = utils;
        this.request = request;
    }

    public byte[] collectionRequest(String artistUri, Constants.SPOTIFY_TASK_TYPE type) throws Exception {
        SpotifyCollectionsProto spotifyCollectionsProto = new SpotifyCollectionsProto(session,utils,artistUri,type);
        byte[] post_body = spotifyCollectionsProto.create();
        byte[] decoded_post_data = Base64.getDecoder().decode(post_body);
        logger.info("{} - followRequest() - post data: {}",session.getEmailAddress(),Base64.getEncoder().encodeToString(decoded_post_data));

        String url = "https://spclient.wg.spotify.com/collection/v2/write";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/vnd.collection-v2.spotify.proto");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("X-Client-Id",session.getVersion().getClientID());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",SpotifyConstants.SP_OS_TYPE);
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());

        request.setContent_type("application/vnd.collection-v2.spotify.proto");
        Headers headers = Headers.of(header);
        Response response = request.post_request(url,decoded_post_data,headers);
        int status_code = response.code();

        if(status_code != 200){
            logger.error("{} - collectionRequest() - got error. returned {} status code. {}",session.getEmailAddress(),status_code,response.body().string());
            return null;
        }else{
            logger.info("{} - collectionRequest() - status code: {} ",session.getEmailAddress(),status_code);
            return "success".getBytes();
        }
    }
}
