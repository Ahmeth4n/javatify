package com.magnakod.emulator.proto.objects.register;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.proto.generated.register.SpRegisterResponse;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterProto implements ProtoActions {

    private final SpotifySession session;
    private final Logger logger = LoggerFactory.getLogger(RegisterProto.class);
    public RegisterProto(SpotifySession session) {
        this.session = session;
    }

    @Override
    public byte[] create() {
        return new byte[0];
    }

    @Override
    public Object parse(byte[] parseObject) {
        SpRegisterResponse.RegisterInformation register_response = null;
        try {
            register_response = SpRegisterResponse.SPRegisterResponse.parseFrom(parseObject).getRegisterInformation();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        logger.warn("parse() - register response serialized: {} ", register_response.getRegisterToken());
        session.setRegisterToken(register_response.getRegisterToken());
        session.setGeneratedUsername(register_response.getUsername());
        return session;
    }
}
