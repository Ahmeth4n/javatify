package com.magnakod.service.accounts;

import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.entity.Accounts;
import com.magnakod.repository.AccountsRepository;

public interface AccountsService {
    void sessionUpdate(Accounts accounts,SpotifySession session, SpotifyHeaders headers);
    void save(SpotifySession session, SpotifyHeaders headers);
}
