package com.magnakod.emulator.proto.objects;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.register.SpLoginAfterRegisterFirst;
import com.magnakod.emulator.session.SpotifySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class GetFirstLoginMessage extends SpotifyBase {
    private final SpotifySession session;
    private final SpotifyConstants consts;
    private final SpotifyHeaders headers;
    private final Logger logger = LoggerFactory.getLogger(GetFirstLoginMessage.class);

    public GetFirstLoginMessage(SpotifyHeaders headers, SpotifyConstants constants, SpotifySession session) {
        super(headers, constants, session);
        this.session = session;
        this.headers = headers;
        this.consts = constants;
    }

    public byte[] make_first_login(){
        SpLoginAfterRegisterFirst.DeviceInformation device_information = SpLoginAfterRegisterFirst.DeviceInformation.newBuilder()
                .setDeviceId(headers.getDeviceId())
                .setClientId(consts.SP_CLIENT_ID)
                .build();

        SpLoginAfterRegisterFirst.UserInformation user_information = SpLoginAfterRegisterFirst.UserInformation.newBuilder()
                .setRegisterToken(session.getRegisterToken())
                .build();

        SpLoginAfterRegisterFirst.SPLoginAfterRegisterFirst register_field = SpLoginAfterRegisterFirst.SPLoginAfterRegisterFirst.newBuilder()
                .setDeviceInformation(device_information)
                .setUserInformation(user_information)
                .build();

        logger.info("make_first_login() - user register token: "+session.getRegisterToken());
        return Base64.getEncoder().encode(register_field.toByteArray());

    }

}
