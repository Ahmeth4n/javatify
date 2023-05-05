package com.magnakod.emulator.proto.objects.client_token;

import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.clientToken.SpClientToken;

public class ClientTokenFirst implements ProtoActions {

    private final SpotifyHeaders headers;
    public ClientTokenFirst(SpotifyHeaders headers){
        this.headers = headers;
    }
    @Override
    public byte[] create() {
        SpClientToken.InnerNumbers client_field_3_inner_numbers = SpClientToken.InnerNumbers.newBuilder()
                .setFirstNum(480)
                .setFirstNum2(800)
                .setFirstNum3(320)
                .setFirstNum4(240)
                .setFirstNum5(240)
                .build();

        SpClientToken.Field3DoubleInner client_field_3_double = SpClientToken.Field3DoubleInner.newBuilder()
                .setInnerNumber(client_field_3_inner_numbers)
                .setUnknownStrVal(SpotifyConstants.ANDROID_OS_VERSION_11)
                .setUnknownIntVal(SpotifyConstants.ANDROID_TARGET_SDK_30)
                .setOsSdk(headers.getOsSdk())
                .setOsSdkRepeat(headers.getOsSdk())
                .setOsType(headers.getOsType())
                .setUnknownText("unknown")
                .setUnknownIntVal2(32)
                .build();

        SpClientToken.Field3Inner client_field_3_inner = SpClientToken.Field3Inner.newBuilder()
                .setInner(client_field_3_double)
                .build();

        SpClientToken.Field3 client_field_3 = SpClientToken.Field3.newBuilder()
                .setField3(client_field_3_inner)
                .setAppVersion2(headers.getDeviceId())
                .build();

        SpClientToken.DeviceInfo device_info = SpClientToken.DeviceInfo.newBuilder()
                .setDeviceId(headers.getClientId())
                .setAppVersion(headers.getAppVersion())
                .setField3(client_field_3)
                .build();

        SpClientToken.SPClient sp_client = SpClientToken.SPClient.newBuilder()
                .setIntValue(1)
                .setDeviceInfo(device_info)
                .build();

        return sp_client.toByteArray();
    }

    @Override
    public Object parse(byte[] parseObject) {
        return null;
    }
}
