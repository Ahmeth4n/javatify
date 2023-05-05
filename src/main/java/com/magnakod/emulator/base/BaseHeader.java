package com.magnakod.emulator.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseHeader {
    private String SPOTIFY_APP_VERSION_HEADER;
    private String ACCEPT_HEADER;
    private String USER_AGENT_HEADER;
    private String X_CLIENT_ID_HEADER;
    private String CLIENT_TOKEN_HEADER;
    private String APP_PLATFORM_HEADER;
    private String AUTHORIZATON_HEADER;

}
