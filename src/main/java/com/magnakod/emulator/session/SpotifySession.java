package com.magnakod.emulator.session;

import com.magnakod.SpotifyVersion;

public class SpotifySession {
    protected String emailAddress;
    protected String birthday;
    protected String password;
    protected String generatedUsername;
    protected String firstName;
    protected String registerToken;
    protected String authorizationToken;
    protected boolean verification_result;
    protected SpotifyVersion version;
    public SpotifyVersion getVersion() {
        return version;
    }

    public void setVersion(SpotifyVersion version) {
        this.version = version;
    }
    public boolean isVerification_result() {
        return verification_result;
    }

    public void setVerification_result(boolean verification_result) {
        this.verification_result = verification_result;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGeneratedUsername() {
        return generatedUsername;
    }

    public void setGeneratedUsername(String generatedUsername) {
        this.generatedUsername = generatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRegisterToken() {
        return registerToken;
    }

    public void setRegisterToken(String registerToken) {
        this.registerToken = registerToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    @Override
    public String toString() {
        return "SpotifySession{" +
                "emailAddress='" + emailAddress + '\'' +
                ", birthday='" + birthday + '\'' +
                ", password='" + password + '\'' +
                ", generatedUsername='" + generatedUsername + '\'' +
                ", firstName='" + firstName + '\'' +
                ", registerToken='" + registerToken + '\'' +
                ", authorizationToken='" + authorizationToken + '\'' +
                ", verification_result=" + verification_result +
                '}';
    }
}
