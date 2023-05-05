package com.magnakod.emulator;

import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.SpotifyUtils;

public class SpotifyBase {

    private SpotifyHeaders headers;
    private SpotifyConstants constants;
    private SpotifyUtils utils;
    private SpotifySession session;
    private BaseRequest request;

    public SpotifyBase(SpotifyConstants constants, BaseRequest request, SpotifyUtils utils) {
        this.utils = utils;
        this.constants = constants;
        this.request = request;
    }
    public SpotifyBase(SpotifyUtils utils) {
        this.utils = utils;
    }
    public SpotifyBase(SpotifyUtils utils,SpotifySession session) {
        this.session = session;
        this.utils = utils;
    }
    public SpotifyBase(SpotifyUtils utils,SpotifySession session,SpotifyConstants consts) {
        this.session = session;
        this.utils = utils;
        this.constants = consts;
    }
    public SpotifyBase(SpotifyHeaders headers, BaseRequest request) {
        this.headers = headers;
        this.request = request;
    }public SpotifyBase(SpotifyHeaders headers, SpotifyConstants constants, SpotifySession session) {
        this.headers = headers;
        this.constants = constants;
        this.session = session;
    }

    public SpotifyBase(SpotifyHeaders headers, SpotifyConstants constants, BaseRequest request) {
        this.headers = headers;
        this.constants = constants;
        this.request = request;

    }

    public SpotifyBase(SpotifyHeaders headers, SpotifyConstants constants, SpotifyUtils utils, BaseRequest request) {
        this.headers = headers;
        this.constants = constants;
        this.utils = utils;
        this.request = request;

    }

    public SpotifyBase(SpotifyHeaders headers, SpotifyConstants constants, SpotifyUtils utils, SpotifySession session, BaseRequest request) {
        this.headers = headers;
        this.constants = constants;
        this.utils = utils;
        this.session = session;
        this.request = request;

    }

    public SpotifyBase(SpotifySession session, SpotifyHeaders headers,BaseRequest request) {
        this.session = session;
        this.headers = headers;
        this.request = request;
    }
    public SpotifyBase(SpotifyHeaders headers, SpotifyUtils utils, SpotifySession session, BaseRequest request) {
        this.session = session;
        this.headers = headers;
        this.request = request;
        this.utils = utils;
    }

    public SpotifyBase(SpotifyHeaders headers, SpotifySession session) {
        this.headers = headers;
        this.session = session;
    }
}
