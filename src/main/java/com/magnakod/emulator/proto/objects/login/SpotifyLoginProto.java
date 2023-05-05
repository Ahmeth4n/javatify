package com.magnakod.emulator.proto.objects.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.magnakod.emulator.ProtoActions;
import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.login.SpotifyLogin;
import com.magnakod.emulator.proto.generated.login.SpotifyLoginV5;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.HashCash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SpotifyLoginProto extends SpotifyBase implements ProtoActions {
    private final SpotifySession session;
    private final SpotifyHeaders headers;
    private final Logger logger = LoggerFactory.getLogger(SpotifyLogin.class);

    public SpotifyLoginProto(SpotifySession session, SpotifyHeaders headers) {
        super(headers, session);
        this.session = session;
        this.headers = headers;
    }

    @Override
    public byte[] create() {
        SpotifyLoginV5.ChallengeType challengeType = SpotifyLoginV5.ChallengeType.newBuilder()
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .addChallengeId(16)
                .build();

        SpotifyLoginV5.DeviceCred deviceCred = SpotifyLoginV5.DeviceCred.newBuilder()
                .setDeviceId(headers.getDeviceId())
                .setClientId(headers.getClientId())
                .build();

        SpotifyLoginV5.UserInformation userInformation = SpotifyLoginV5.UserInformation.newBuilder()
                .setEmail(session.getEmailAddress())
                .setPassword(session.getPassword())
                .setChallengeType(challengeType).
                build();

        SpotifyLoginV5.LoginMessage loginMessage = SpotifyLoginV5.LoginMessage.newBuilder()
                .setDeviceCred(deviceCred)
                .setUserInformation(userInformation)
                .build();
        return loginMessage.toByteArray();
    }

    @Override
    public Object parse(byte[] loginMessage) {
        SpotifyLoginV5.LoginResponse loginResponse = null;
        try {
            loginResponse = SpotifyLoginV5.LoginResponse.parseFrom(loginMessage);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        byte[] tokenByteArr = loginResponse.getLoginToken().toByteArray();
        String tokenStr = Base64.getEncoder().encodeToString(tokenByteArr);

        byte[] challengeToken = loginResponse.getCreds().getChallengeObject().getChallengeCredentials().getChallengeToken().toByteArray();
        String challengeTokenStr = Base64.getEncoder().encodeToString(challengeToken);

        logger.info("{} - stored token parsed: {}",session.getEmailAddress(),tokenStr);
        headers.setTemporaryClientToken(tokenStr);

        HashCash hashCash = new HashCash();
        String hashcash_token = null;
        try {
            hashcash_token = hashCash.solveChallenge(challengeTokenStr,loginResponse.getCreds().getChallengeObject().getChallengeCredentials().getChallengeType());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        logger.info("{} - hashcash challenge solved : {}",session.getEmailAddress(),hashcash_token);
        return null;
    }
}
