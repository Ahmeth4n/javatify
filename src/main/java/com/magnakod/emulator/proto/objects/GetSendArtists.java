package com.magnakod.emulator.proto.objects;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.artists.ArtistsChose;
import com.magnakod.emulator.proto.objects.artists.SpotifyArtistsProto;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;
import okhttp3.Headers;

import java.util.Base64;
import java.util.HashMap;

public class GetSendArtists extends SpotifyBase {

    private final SpotifyConstants constants;
    private final BaseRequest request;
    private final SpotifyHeaders headers;
    private final SpotifySession session;

    public GetSendArtists(SpotifySession session,SpotifyHeaders headers, SpotifyConstants constants, BaseRequest request, SpotifyUtils utils) {
        super(headers, constants, utils, session, request);
        this.headers = headers;
        this.constants = constants;
        this.request = request;
        this.session = session;
    }

    public String makePayload(){
        ArtistsChose.Artists artists = ArtistsChose.Artists.newBuilder()
                .setArtistUris("spotify:artist:7dGJo4pcD2V6oG8kP0tJRR")
                .build();
        ArtistsChose.Artists artists2 = ArtistsChose.Artists.newBuilder()
                .setArtistUris("spotify:artist:55Aa2cqylxrFIXC767Z865")
                .build();
        ArtistsChose.Artists artists3 = ArtistsChose.Artists.newBuilder()
                .setArtistUris("spotify:artist:7hJcb9fa4alzcOq3EaNPoG")
                .build();
        ArtistsChose.ArtistData artistData = ArtistsChose.ArtistData.newBuilder()
                .addArtists(0,artists)
                .addArtists(1,artists2)
                .addArtists(2,artists3)
                .build();
        return Base64.getEncoder().encodeToString(artistData.toByteArray());
    }

    public String sendArtist(String sessionId) throws Exception {
        String url = "https://spclient.wg.spotify.com/allboarding/v1/onboarding/complete?deeplink=&action=COMPLETE_FLOW&model="+constants.SP_OS_SDK+"&step=ARTIST&entry-point=SIGNUP&platform=android&flow=ARTIST_CONTEXTUAL_AUDIO&manufacturer=unknown&session-id="+sessionId;
        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("Spotify-App-Version",session.getVersion().getAppVersion());
        header.put("User-Agent",session.getVersion().getUserAgent());
        header.put("X-Client-Id",session.getVersion().getClientID());
        header.put("Client-Token",headers.getClientToken());
        header.put("App-Platform",SpotifyConstants.SP_OS_TYPE);
        header.put("Authorization","Bearer "+ session.getAuthorizationToken());

        SpotifyArtistsProto spotifyArtistsProto = new SpotifyArtistsProto();
        byte[] postData = spotifyArtistsProto.create();

        request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        String response = request.post_request(url,postData,headers).body().string();
        return response;
    }

}
