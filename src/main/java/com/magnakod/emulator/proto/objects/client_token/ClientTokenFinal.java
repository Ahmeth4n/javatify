package com.magnakod.emulator.proto.objects.client_token;

import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.clientToken.SpClientWithToken;

public class ClientTokenFinal implements ProtoActions {

    private final String hashcashToken;
    private final String clientToken;

    /**
     * @param hashcashToken is a first request hashcash suffix solution value.
     */
    public ClientTokenFinal(String hashcashToken, String clientToken){
        this.hashcashToken = hashcashToken;
        this.clientToken = clientToken;
    }

    @Override
    public byte[] create() {
        SpClientWithToken.TokenInfoInnerSub hashcash_solve = SpClientWithToken.TokenInfoInnerSub.newBuilder()
                .setUnknownToken(hashcashToken)
                .build();

        SpClientWithToken.TokenInfoInner hashcash_type_solve = SpClientWithToken.TokenInfoInner.newBuilder()
                .setUnknownIntVal(3) // challenge type
                .setTokenSub(hashcash_solve)
                .build();

        SpClientWithToken.TokenInfo token_info = SpClientWithToken.TokenInfo.newBuilder()
                .setClientToken(clientToken)
                .setTokenInner(hashcash_type_solve)
                .build();

        SpClientWithToken.SPClientWithToken client_token_message = SpClientWithToken.SPClientWithToken.newBuilder()
                .setUnknownIntVal(2) //challenge value
                .setTokenInfo(token_info)
                .build();

        return client_token_message.toByteArray();
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
