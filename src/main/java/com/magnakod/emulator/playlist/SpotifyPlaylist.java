package com.magnakod.emulator.playlist;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.objects.playlist.SpotifyPlaylistKeyProto;
import com.magnakod.emulator.proto.objects.playlist.SpotifyPlaylistProto;
import com.magnakod.emulator.session.SpotifySession;
import okhttp3.Headers;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;

public class SpotifyPlaylist extends SpotifyBase {
    private final Logger logger = LoggerFactory.getLogger(SpotifyPlaylist.class);
    private final SpotifyHeaders headers;
    private final SpotifySession session;
    private final BaseRequest request;
    public SpotifyPlaylist(SpotifyHeaders headers, SpotifySession session, BaseRequest request) {
        super(session, headers,request);
        this.session = session;
        this.headers = headers;
        this.request = request;
    }

    public String getPlaylistKey() throws Exception {
        String url = "https://spclient.wg.spotify.com/playlist/v2/user/"+session.getGeneratedUsername()+"/rootlist?decorate=revision%2Cattributes%2Clength%2Cowner%2Ccapabilities&from=0&length=120";
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/vnd.collection-v2.spotify.proto");
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Spotify-Accept-Geoblock","dummy");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform","Android");
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());

        request.setContent_type("application/octet-stream");
        Headers headers = Headers.of(header);
        Response response = request.get_request(url,headers);

        logger.info("{} - response status code: {}",session.getGeneratedUsername(),response.code());
        byte[] responseBody = response.body().bytes();

        return Base64.getEncoder().encodeToString(responseBody);
    }
    public String followPlaylist(String playlistUri) throws Exception {
        String playlistKey = getPlaylistKey();

        SpotifyPlaylistKeyProto spotifyPlaylistKeyProto = new SpotifyPlaylistKeyProto();
        byte[] bytePlaylistKey = spotifyPlaylistKeyProto.parse(Base64.getDecoder().decode(playlistKey));

        logger.info("{} - playlist key parsed: {}",session.getEmailAddress(),Base64.getEncoder().encodeToString(bytePlaylistKey));

        SpotifyPlaylistProto playlistProto = new SpotifyPlaylistProto(headers,session,bytePlaylistKey,playlistUri);
        byte[] protoMessage = playlistProto.create();
        logger.info("{} - playlist follow proto message: {}",session.getEmailAddress(),Base64.getEncoder().encodeToString(protoMessage));

        String url = "https://spclient.wg.spotify.com/playlist/v2/user/"+session.getGeneratedUsername()+"/rootlist/changes";
        HashMap<String,String> header = new HashMap<>();
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("Spotify-Accept-Geoblock","dummy");
        header.put("Spotify-Playlist-Sync-Reason","CAk=");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform","Android");

        request.setContent_type("text/plain");
        Headers headers = Headers.of(header);
        Response response = request.post_request(url,protoMessage,headers);

        byte[] responseBody = response.body().bytes();
        logger.info("{} - playlist follow response: {}",session.getGeneratedUsername(),Base64.getEncoder().encodeToString(responseBody));

        return Base64.getEncoder().encodeToString(responseBody);
    }

    public byte[] unfollowPlaylist(){
        return null;
    }
}
