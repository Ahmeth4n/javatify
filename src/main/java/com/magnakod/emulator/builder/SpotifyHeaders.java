package com.magnakod.emulator.builder;

import com.google.gson.annotations.SerializedName;

public class SpotifyHeaders {
    @SerializedName("device_id")
    protected String deviceId;
    @SerializedName("app_version")
    protected String appVersion;
    protected String locale;
    @SerializedName("client_id")
    protected String clientId;
    @SerializedName("client_token")
    protected String clientToken;
    @SerializedName("temporary_client_token")
    protected String temporaryClientToken;
    @SerializedName("os_sdk")
    protected String osSdk;
    @SerializedName("os_type")
    protected String osType;

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getTemporaryClientToken() {
        return temporaryClientToken;
    }

    public void setTemporaryClientToken(String temporaryClientToken) {
        this.temporaryClientToken = temporaryClientToken;
    }

    public String getOsSdk() {
        return osSdk;
    }

    public void setOsSdk(String osSdk) {
        this.osSdk = osSdk;
    }

    @Override
    public String toString() {
        return "SpotifyHeaders{" +
                "deviceId='" + deviceId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", locale='" + locale + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientToken='" + clientToken + '\'' +
                ", temporaryClientToken='" + temporaryClientToken + '\'' +
                ", osSdk='" + osSdk + '\'' +
                ", osType='" + osType + '\'' +
                '}';
    }
}
