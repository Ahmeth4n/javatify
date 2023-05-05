package com.magnakod.emulator.proto.objects.client_token;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.clientToken.SpClientTokenResponse;

import java.util.HashMap;

public class SpotifyClientTokenProto implements ProtoActions {
    @Override
    public byte[] create() {
        return new byte[0];
    }

    @Override
    public Object parse(byte[] parseObject) {
        SpClientTokenResponse.ClientToken token = null;
        HashMap<String,Object> clientTokenMap = null;
        try {
            token = SpClientTokenResponse.SPClientTokenResponse.parseFrom(parseObject).getClientToken();

            clientTokenMap = new HashMap<>();
            clientTokenMap.put("hashcash_length",SpClientTokenResponse.SPClientTokenResponse.parseFrom(parseObject).getClientToken().getClientTokenSub().getSubInner().getUnknownIntValue());
            clientTokenMap.put("hashcash_token",SpClientTokenResponse.SPClientTokenResponse.parseFrom(parseObject).getClientToken().getClientTokenSub().getSubInner().getUnknownToken());
            clientTokenMap.put("client_token",token.getClientToken());

        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        return clientTokenMap;
    }
}
