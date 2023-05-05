package com.magnakod.emulator.proto.objects;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterLast;
import com.magnakod.emulator.session.SpotifySession;

import java.util.Base64;

public class GetLastLoginMessage extends SpotifyBase {
    private final SpotifySession session;
//    private SpotifyConstants consts;
    private final SpotifyHeaders headers;

    public GetLastLoginMessage(SpotifyHeaders headers, SpotifySession session) {
        super(headers,session);
        this.session = session;
        this.headers = headers;
//        this.consts = constants;
    }

    public byte[] make_last_login(){
        SpLoginAfterRegisterLast.DeviceInformation device_information = SpLoginAfterRegisterLast.DeviceInformation.newBuilder()
                .setDeviceId(headers.getDeviceId())
//                .setClientId(consts.SP_CLIENT_ID)
                .setClientId(headers.getClientId())
                .build();
        SpLoginAfterRegisterLast.UserInformation user_information = SpLoginAfterRegisterLast.UserInformation.newBuilder()
                .setUsername(session.getGeneratedUsername())
                .setClientToken(headers.getTemporaryClientToken())
                .build();

        SpLoginAfterRegisterLast.SPLoginAfterRegisterLast last_login = SpLoginAfterRegisterLast.SPLoginAfterRegisterLast.newBuilder()
                .setDeviceInformation(device_information)
                .setUserInformation(user_information)
                .build();
        return Base64.getEncoder().encode(last_login.toByteArray());
    }

}
