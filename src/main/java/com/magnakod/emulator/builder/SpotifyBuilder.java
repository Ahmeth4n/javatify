package com.magnakod.emulator.builder;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.utils.SpotifyUtils;

public class SpotifyBuilder extends SpotifyBase {
    private final SpotifyConstants constants;
    private String locale;
    private final  SpotifyUtils utils;
    public SpotifyBuilder(SpotifyConstants constants, BaseRequest request, SpotifyUtils utils) {
        super(constants, request, utils);
        this.constants = constants;
        this.utils = utils;
    }
    public SpotifyHeaders build(){
        SpotifyHeaders headers = new SpotifyHeaders();
        if (locale == null){
            locale = constants.SP_DEFAULT_LOCALE;
        }

        headers.setAppVersion(constants.SP_APP_VERSION_8_7_78);
        headers.setClientId(constants.SP_CLIENT_ID);
        headers.setLocale(this.locale);
        headers.setDeviceId(utils.getDeviceID());
        headers.setOsSdk(constants.SP_OS_SDK);
        headers.setOsType(constants.SP_OS_TYPE);
        return headers;
    }

}
