package com.magnakod.emulator.proto.objects.collections;

import com.magnakod.Constants;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.follow.SpotifyFollowOuterClass;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;

import java.util.Base64;

public class SpotifyCollectionsProto implements ProtoActions {

    private final SpotifySession session;
    private final SpotifyUtils utils;
    private final String spotifyUri;
    private final Constants.SPOTIFY_TASK_TYPE taskType;
    public SpotifyCollectionsProto(SpotifySession session, SpotifyUtils utils, String spotifyUri, Constants.SPOTIFY_TASK_TYPE type) {
        this.session = session;
        this.utils = utils;
        this.spotifyUri = spotifyUri;
        this.taskType = type;
    }

    @Override
    public byte[] create() {
        int time = (int) (System.currentTimeMillis() / 1000);
        SpotifyFollowOuterClass.ActionInformation actionInformation = SpotifyFollowOuterClass.ActionInformation.newBuilder()
                .setSpotifyUri(spotifyUri)
                .setTimestampValue(time)
                .build();

        SpotifyFollowOuterClass.SpotifyFollow spotifyFollowOuterClass = SpotifyFollowOuterClass.SpotifyFollow.newBuilder()
                .setUsername(session.getGeneratedUsername())
                .setActionType(utils.getCollectionType(taskType))
                .setActionInformation(actionInformation)
                .setRandomValue(utils.randomCollectionID())
                .build();

        return Base64.getEncoder().encode(spotifyFollowOuterClass.toByteArray());
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
