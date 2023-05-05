package com.magnakod.emulator;

import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;

public class SpotifySessionModel {
    private final SpotifySession session;
    private final SpotifyHeaders headers;
    public SpotifySession getSession() {
        return session;
    }
    public SpotifyHeaders getHeaders() {
        return headers;
    }
    public SpotifySessionModel(SpotifySession session, SpotifyHeaders headers) {
        this.session = session;
        this.headers = headers;
    }

}
