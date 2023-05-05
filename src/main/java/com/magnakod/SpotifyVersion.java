package com.magnakod;

import com.magnakod.emulator.SpotifyConstants;

public class SpotifyVersion {
    public String osSdk;
    public String clientID;
    public String appVersion;
    public String userAgent;
    public int sdkVersion;
    public String osVersion;

    public String getOsSdk() {
        return osSdk;
    }

    public String getClientID() {
        return clientID;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getSdkVersion() {
        return sdkVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public SpotifyConstants getConstants() {
        return constants;
    }

    private final SpotifyConstants constants;

    public enum VERSION{
        SPOTIFY_8_7_78
    };
    public SpotifyVersion(SpotifyConstants constants) {
        this.constants = constants;
    }
    public SpotifyVersion getVersion(VERSION version){
        switch (version){
            case SPOTIFY_8_7_78:
                this.appVersion = SpotifyConstants.SP_APP_VERSION_8_7_78;
                this.osSdk = SpotifyConstants.SP_OS_SDK;
                this.osVersion = SpotifyConstants.ANDROID_OS_VERSION_11;
                this.clientID = constants.SP_CLIENT_ID;
                this.sdkVersion = SpotifyConstants.ANDROID_TARGET_SDK_30;
                this.userAgent = String.format(constants.SP_ANDROID_USER_AGENT,this.appVersion,this.sdkVersion,SpotifyConstants.SP_OS_SDK);
                break;
        };
        return this;
    }
    @Override
    public String toString() {
        return "SpotifyVersion{" +
                "osSdk='" + osSdk + '\'' +
                ", clientID='" + clientID + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", sdkVersion=" + sdkVersion +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
