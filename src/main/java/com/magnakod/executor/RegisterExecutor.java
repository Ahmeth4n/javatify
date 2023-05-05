package com.magnakod.executor;

import com.magnakod.emulator.Spotify;
import com.magnakod.emulator.runner.SpotifyRegisterRunner;
import com.magnakod.repository.AccountsRepository;
import com.magnakod.service.accounts.AccountsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.magnakod.SpotifyPlatformApplication.getSpotifyBean;

@Component
@Async
public class RegisterExecutor {
    private final AtomicInteger usersRegistered = new AtomicInteger();
    private final AtomicInteger totalRegistrationError = new AtomicInteger(0);
    private final AtomicInteger totalProxyConnectionError = new AtomicInteger(0);
    private final AtomicInteger totalThreadCount;
    private final AccountsServiceImpl accountsService;
    private final Spotify spotify;
    private final Logger logger = LoggerFactory.getLogger(RegisterExecutor.class);
    private final AtomicBoolean isProxyEnabled = new AtomicBoolean(false);
    private final Queue<Integer> registrationQueue = new LinkedBlockingDeque<>();
    private final int accountCount;

    public RegisterExecutor(AccountsServiceImpl accountsService,Spotify spotify,
                            boolean proxyStatus,int threadCount,int accountCount){
        totalThreadCount = new AtomicInteger(threadCount);
        this.accountsService = accountsService;
        this.spotify = spotify;
        this.accountCount = accountCount;
        isProxyEnabled.set(proxyStatus);
        fillQueue();
    }
    public void fillQueue(){
        for (int i = 0; i < accountCount;i++){
            registrationQueue.add(accountCount);
        }
    }
    public void execute(){
        for (int currentCount = 0; currentCount < totalThreadCount.get();currentCount++){
            Runnable runnable = () -> {
                while(!registrationQueue.isEmpty()){
                    registrationQueue.poll();

                    SpotifyRegisterRunner registerRunner = new SpotifyRegisterRunner(accountsService,spotify);
                    try {
                        boolean registerStatus = registerRunner.register(isProxyEnabled.get());
                        if (registerStatus){
                            usersRegistered.getAndIncrement();
                        }else{
                            logger.info("register() - user not registered: register() returned false.");
                        }
                    } catch (TimeoutException e) {
                        totalProxyConnectionError.getAndIncrement();
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        totalRegistrationError.getAndIncrement();
                        throw new RuntimeException(e);
                    } finally {
                        logger.info("usersRegistered - total registered user: {}", usersRegistered.get());
                        logger.info("totalProxyConnectionError - total proxy error: {}", totalProxyConnectionError.get());
                        logger.info("totalRegistrationError - total registration error: {}", totalRegistrationError.get());
                        logger.info("loaded sessions : {}",spotify.getSessionCount());
                    }
                }

            };

            Thread thread = new Thread(runnable);
            thread.setName("registration-thread-"+currentCount);
            thread.start();

            logger.info("thread started for registration process: {} ", thread.getName());
        }

    }
}