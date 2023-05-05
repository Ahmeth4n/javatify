package com.magnakod.emulator.proto.objects.register;

import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterLast;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class RegisterLoginLast implements ProtoActions {
    private final SpotifySession session;
    private final SpotifyHeaders headers;

    public RegisterLoginLast(SpotifySession session, SpotifyHeaders headers){
        this.session = session;
        this.headers = headers;
    }
    @Override
    public byte[] create() {
        SpLoginAfterRegisterLast.DeviceInformation device_information = SpLoginAfterRegisterLast.DeviceInformation.newBuilder()
                .setDeviceId(headers.getDeviceId())
                .setClientId(session.getVersion().getClientID())
                .build();
        SpLoginAfterRegisterLast.UserInformation user_information = SpLoginAfterRegisterLast.UserInformation.newBuilder()
                .setUsername(session.getGeneratedUsername())
                .setClientToken(headers.getTemporaryClientToken())
                .build();

        SpLoginAfterRegisterLast.SPLoginAfterRegisterLast last_login = SpLoginAfterRegisterLast.SPLoginAfterRegisterLast.newBuilder()
                .setDeviceInformation(device_information)
                .setUserInformation(user_information)
                .build();

        return last_login.toByteArray();
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
