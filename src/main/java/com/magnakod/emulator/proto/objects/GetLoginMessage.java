package com.magnakod.emulator.proto.objects;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.proto.generated.register.SpRegister;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;

import java.util.Base64;

public class GetLoginMessage extends SpotifyBase {

    private final SpotifySession session;
    private final SpotifyConstants consts;

    public GetLoginMessage(SpotifyUtils utils, SpotifySession session, SpotifyConstants consts) {
        super(utils, session, consts);
        this.session = session;
        this.consts = consts;
    }

    public String register_payload(String device_id) throws Exception{
        SpRegister.Field4_1 field4_1 = SpRegister.Field4_1.newBuilder()
                .setIntValue(1)
                .build();

        SpRegister.EmptyField emptyField = SpRegister.EmptyField.newBuilder().build();

        SpRegister.Field4 field4 = SpRegister.Field4.newBuilder()
                .setField4(field4_1)
                .setEmptyField(emptyField)
                .setEmptyField2(emptyField)
                .setEmptyField3(emptyField)
                .build();

        SpRegister.RegisterInformation register_information = SpRegister.RegisterInformation.newBuilder()
                .setEmail(session.getEmailAddress())
                .setPassword(session.getPassword())
                .build();

        SpRegister.AccountInformation account_information = SpRegister.AccountInformation.newBuilder()
                .setFirstName(session.getFirstName())
                .setBirthday(session.getBirthday())
                .setIntValue(1)
                .setField4(field4)
                .setRegister(register_information)
                .build();

        SpRegister.DeviceInformation device_information = SpRegister.DeviceInformation.newBuilder()
                .setSignupKey(SpotifyConstants.SP_SIGN_UP_KEY)
                .setDeviceCpuInfo(SpotifyConstants.SP_DEVICE_CPU_INFO)
                .setAppVersion(SpotifyConstants.SP_APP_VERSION_8_7_78)
                .setIntValue("\\u0001")
                .setDeviceId(device_id)
                .build();
        SpRegister.ClientMobile client_mobile = SpRegister.ClientMobile.newBuilder()
                .setClientMobile("client_mobile")
                .build();

        SpRegister.SPRegisterSchema sp_register = SpRegister.SPRegisterSchema.newBuilder()
                .setRequestUrl(consts.SP_REGISTER_ENDPOINT)
                .setAccount(account_information)
                .setDeviceInfo(device_information)
                .setClientMobile(client_mobile)
                .build();

        byte[] register_proto = sp_register.toByteArray();

        return Base64.getEncoder().encodeToString(register_proto);
    }

}
