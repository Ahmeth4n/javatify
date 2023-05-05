package com.magnakod.emulator.proto.objects.playlist;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.playlist.GetPlaylistKeyOuterClass;

public class SpotifyPlaylistKeyProto implements ProtoActions {
    @Override
    public byte[] create() {
        return new byte[0];
    }

    @Override
    public byte[] parse(byte[] parseObject) {
        try {
            byte[] getPlaylistKey = GetPlaylistKeyOuterClass.GetPlaylistKey.parseFrom(parseObject).getPlaylistKey().toByteArray();
            return getPlaylistKey;
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

    }
}
