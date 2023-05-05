package com.magnakod.emulator.proto.objects.artists;

import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.artists.ArtistsChose;

import java.util.Base64;

public class SpotifyArtistsProto implements ProtoActions {
    @Override
    public byte[] create() {
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
        return artistData.toByteArray();
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
