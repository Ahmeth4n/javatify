package com.magnakod.spotifyplatform;

import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.client_token.SpotifyClientToken;
import com.magnakod.emulator.login.SpotifyLogin;
import com.magnakod.emulator.playlist.SpotifyPlaylist;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.entity.Accounts;
import com.magnakod.repository.AccountsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SpotifyLoginTest {
    private final Logger logger = LoggerFactory.getLogger(SpotifyLoginTest.class);
    @Autowired
    private AccountsRepository accountsRepository;

    @Test
    public void loginTest() {
        Accounts account = accountsRepository.findAccountsByIdIs("63f4ad0535496f56abce1c01");
        SpotifySession session = account.getSession();
        SpotifyHeaders headers = account.getHeaders();
        BaseRequest request = new BaseRequest();

        String oldTemporaryToken = headers.getTemporaryClientToken();
        SpotifyLogin spotifyLogin = new SpotifyLogin(headers, session,request);

        try {

            SpotifyClientToken clientToken = new SpotifyClientToken(session,headers,new SpotifyConstants(),request);
            String clientTokenStr = clientToken.get_full_token();
            logger.info("{} - get new client token: {}",session.getEmailAddress(),clientTokenStr);
            headers.setClientToken(clientTokenStr);

            spotifyLogin.login();
            String newTemporaryToken = headers.getTemporaryClientToken();
            assertNotEquals(oldTemporaryToken,newTemporaryToken);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
