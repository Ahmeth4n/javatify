package com.magnakod.emulator.proto.objects.playlist;

import com.google.protobuf.ByteString;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.playlist.GetPlaylistAction;
import com.magnakod.emulator.session.SpotifySession;

import java.time.Instant;

public class SpotifyPlaylistProto extends SpotifyBase implements ProtoActions {
    private final byte[] playlistToken;
    private final String playlistUrl;
    private final SpotifySession session;
    public SpotifyPlaylistProto(SpotifyHeaders headers, SpotifySession session, byte[] playlistToken, String playlistUri) {
        super(headers, session);
        this.playlistToken = playlistToken;
        this.playlistUrl = playlistUri;
        this.session = session;
    }

    @Override
    public byte[] create() {
        long timestamp = Instant.EPOCH.toEpochMilli();

        GetPlaylistAction.PlaylistTime playlistTime = GetPlaylistAction.PlaylistTime.newBuilder()
                .setUnknownInt(0)
                .setTimestamp(timestamp)
                .build();

        GetPlaylistAction.PlaylistUri playlistUri = GetPlaylistAction.PlaylistUri.newBuilder()
                .setPlaylistUrl(playlistUrl)
                .setPlaylistTime(playlistTime)
                .build();

        GetPlaylistAction.PlaylistObjectInner playlistObjectInner = GetPlaylistAction.PlaylistObjectInner.newBuilder()
                .setUnknownInt(0)
                .setPlaylistUri(playlistUri)
                .build();

        GetPlaylistAction.PlaylistObject playlistObject = GetPlaylistAction.PlaylistObject.newBuilder()
                .setUnknownInt(2)
                .setPlaylistObj(playlistObjectInner)
                .build();

        GetPlaylistAction.PlaylistCreds playlistCreds = GetPlaylistAction.PlaylistCreds.newBuilder()
                .setUsername(session.getGeneratedUsername())
                .setTimestamp(timestamp)
                .build();

        GetPlaylistAction.PlaylistDetails playlistDetails = GetPlaylistAction.PlaylistDetails.newBuilder()
                .setPlaylistCreds(playlistCreds)
                .setPlaylistObject(playlistObject)
                .build();

        GetPlaylistAction.Playlist playlist = GetPlaylistAction.Playlist.newBuilder()
                .setPlaylistToken(ByteString.copyFrom(playlistToken))
                .setUnknownInt(1)
                .setDetails(playlistDetails)
                .setUnknownIntTwo(1)
                .setUnknownIntThree(1)
                .build();

        return playlist.toByteArray();
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
