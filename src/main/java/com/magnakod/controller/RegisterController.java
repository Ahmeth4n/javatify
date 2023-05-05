package com.magnakod.controller;

import com.magnakod.emulator.Spotify;
import com.magnakod.executor.RegisterExecutor;
import com.magnakod.repository.AccountsRepository;
import com.magnakod.responses.BaseResponse;
import com.magnakod.service.accounts.AccountsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.magnakod.SpotifyPlatformApplication.getSpotifyBean;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private AccountsServiceImpl accountsService;
    @Autowired
    private Spotify spotify;
    private final AtomicInteger threadCount = new AtomicInteger(5); //init default value 5
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final AtomicBoolean serverAvailableStatus = new AtomicBoolean(true);

    @PostMapping(path = "/account/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createRegisterTask(int thread, int accountCount, boolean isProxyEnabled){
        if (!serverAvailableStatus.get()){
            logger.error("createRegisterTask() - got error: queue full.");
            return new ResponseEntity<>(new BaseResponse(true, "You have an active process. Please wait for your active process to finish first."),
                    HttpStatus.BAD_REQUEST);
        }

        logger.info("createRegisterTask() -  thread count is {}, proxy status is {}",thread,isProxyEnabled);

        if (thread != 0){
            threadCount.set(thread);
        }

        logger.info("createRegisterTask() - new task received with {} threads. pool created.",threadCount);

        //create new executor
        RegisterExecutor registerExecutor = new RegisterExecutor(accountsService,spotify,isProxyEnabled,threadCount.get(),accountCount);
        registerExecutor.execute();

        //we lock the queue after receiving the process
        serverAvailableStatus.set(true);

        return new ResponseEntity<>(new BaseResponse(true, "Process started successfully."),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/tasks/clear",produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeRegisterTask(){
        if (this.spotify.getSessionCount() != 0){
            this.spotify.createNewSession();
        }
    }

}
