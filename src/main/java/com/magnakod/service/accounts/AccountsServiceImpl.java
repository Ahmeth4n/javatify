package com.magnakod.service.accounts;

import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.entity.Accounts;
import com.magnakod.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AccountsServiceImpl implements AccountsService{
    @Autowired
    private AccountsRepository accountsRepository;
    @Override
    public void sessionUpdate(Accounts accounts, SpotifySession session, SpotifyHeaders headers) {
        accounts.setLastUpdatedDate(new Date());
        accounts.setHeaders(headers);
        accounts.setSession(session);
        accountsRepository.save(accounts);
    }

    @Override
    public void save(SpotifySession session, SpotifyHeaders headers) {
        Accounts accounts = new Accounts();
        accounts.setSession(session);
        accounts.setIs_banned(false);
        accounts.setHeaders(headers);
        accounts.setCreated_date(new Date());
        accounts.setPassword(session.getPassword());
        accounts.setIs_mail_verified(false);
        accounts.setUsername(session.getGeneratedUsername());
        accounts.setCreated_date(new Date());
        accounts.setEmail(session.getEmailAddress());
        accounts.setUsage(false);
        accounts.setLastUpdatedDate(new Date());

        accountsRepository.save(accounts);
    }
}
