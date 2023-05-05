package com.magnakod.emulator.proto.objects.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginResponseAfterRegister;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpotifySecondLoginProto implements ProtoActions {

    private final SpotifySession session;
    private final SpotifyHeaders headers;
    private final Logger logger = LoggerFactory.getLogger(SpotifySecondLoginProto.class);
    public SpotifySecondLoginProto(SpotifySession session, SpotifyHeaders headers) {
        this.headers = headers;
        this.session = session;
    }

    @Override
    public byte[] create() {
        return new byte[0];
    }

    @Override
    public Object parse(byte[] parseObject) {
        SpLoginResponseAfterRegister.LoginMessage registerLoginMessageLast = null;
        try {
            registerLoginMessageLast = SpLoginResponseAfterRegister.SPLoginResponseAfterRegister.parseFrom(parseObject).getLoginMessage();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        logger.info("client token is {} and authorization token is {}", registerLoginMessageLast.getClientToken(),registerLoginMessageLast.getAuthorizationToken());
        session.setAuthorizationToken(registerLoginMessageLast.getAuthorizationToken());
        session.setGeneratedUsername(registerLoginMessageLast.getUsername());
        headers.setTemporaryClientToken(registerLoginMessageLast.getClientToken());

        logger.info("authorizationToken value: {}",session.getAuthorizationToken());
        return null;
    }
}
