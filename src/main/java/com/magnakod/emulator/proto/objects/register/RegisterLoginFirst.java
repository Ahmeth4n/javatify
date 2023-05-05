package com.magnakod.emulator.proto.objects.register;

import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterFirst;
import com.magnakod.emulator.proto.objects.login.SpotifySecondLoginProto;
import com.magnakod.emulator.requests.RegisterLoginRequest;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class RegisterLoginFirst implements ProtoActions {

    private final Logger logger = LoggerFactory.getLogger(RegisterLoginFirst.class);
    private final SpotifySession session;
    private final SpotifyHeaders headers;
    public RegisterLoginFirst(SpotifySession session, SpotifyHeaders headers){
        this.session = session;
        this.headers = headers;
    }
    @Override
    public byte[] create() {
        SpLoginAfterRegisterFirst.DeviceInformation device_information = SpLoginAfterRegisterFirst.DeviceInformation.newBuilder()
                .setDeviceId(headers.getDeviceId())
                .setClientId(session.getVersion().getClientID())
                .build();

        SpLoginAfterRegisterFirst.UserInformation user_information = SpLoginAfterRegisterFirst.UserInformation.newBuilder()
                .setRegisterToken(session.getRegisterToken())
                .build();

        SpLoginAfterRegisterFirst.SPLoginAfterRegisterFirst register_field = SpLoginAfterRegisterFirst.SPLoginAfterRegisterFirst.newBuilder()
                .setDeviceInformation(device_information)
                .setUserInformation(user_information)
                .build();

        logger.info("make_first_login() - register token is: "  + session.getRegisterToken());
        return register_field.toByteArray();

    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
