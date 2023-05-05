package com.magnakod.emulator.proto.objects.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginResponseAfterRegister;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpotifyFirstLoginProto implements ProtoActions {

    private final SpotifySession session;
    private final SpotifyHeaders headers;
    private final Logger logger = LoggerFactory.getLogger(SpotifyFirstLoginProto.class);
    public SpotifyFirstLoginProto(SpotifySession session, SpotifyHeaders headers) {
        this.session = session;
        this.headers = headers;

    }

    @Override
    public byte[] create() {
        return new byte[0];
    }

    @Override
    public Object parse(byte[] parseObject) {
        SpLoginResponseAfterRegister.LoginMessage registerLoginMessage = null;
        try {
            registerLoginMessage = SpLoginResponseAfterRegister.SPLoginResponseAfterRegister.parseFrom(parseObject).getLoginMessage();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        session.setAuthorizationToken(registerLoginMessage.getAuthorizationToken());
        session.setGeneratedUsername(registerLoginMessage.getUsername());
        headers.setTemporaryClientToken(registerLoginMessage.getClientToken());
        logger.info("authorizationToken - value: {}",session.getAuthorizationToken());
        return null;
    }
}
