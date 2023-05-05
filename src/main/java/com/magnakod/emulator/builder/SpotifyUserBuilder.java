package com.magnakod.emulator.builder;

import com.github.javafaker.Faker;
import com.magnakod.SpotifyVersion;
import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;

public class SpotifyUserBuilder extends SpotifyBase {
    private final SpotifyUtils utils;
    private SpotifyVersion version;
    private final SpotifyConstants constants;
    public SpotifyUserBuilder(SpotifyUtils utils, SpotifyConstants constants) {
        super(utils);
        this.utils = utils;
        this.constants = constants;
    }

    public SpotifySession build(){
        Faker faker = new Faker();
        version = new SpotifyVersion(constants).getVersion(SpotifyVersion.VERSION.SPOTIFY_8_7_78);

        SpotifySession session = new SpotifySession();

        String email_address = utils.get_email_address();
        String password = utils.get_random_password();
        String birthday = utils.randomDataOfBirth(1950,2000);

        session.setEmailAddress(email_address);
        session.setPassword(password);
        session.setBirthday(birthday);
        session.setVerification_result(false);
        session.setFirstName(faker.name().firstName().toLowerCase() + faker.name().lastName().toLowerCase());
        session.setVersion(version);


        return session;
    }
}
