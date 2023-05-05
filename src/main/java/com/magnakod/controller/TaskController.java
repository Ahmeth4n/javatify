package com.magnakod.controller;

import com.google.gson.Gson;
import com.magnakod.Constants;
import com.magnakod.emulator.Spotify;
import com.magnakod.entity.Accounts;
import com.magnakod.entity.Tasks;
import com.magnakod.executor.LocalTaskExecutor;
import com.magnakod.mapper.DataTableBody;
import com.magnakod.mapper.DataTableModel;
import com.magnakod.repository.AccountsRepository;
import com.magnakod.repository.TasksRepository;
import com.magnakod.responses.BaseResponse;
import com.magnakod.service.accounts.AccountsServiceImpl;
import com.magnakod.service.mq.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.magnakod.Constants.getTaskType;

@RestController
@RequestMapping("/api")
public class TaskController{
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private AccountsServiceImpl accountsService;
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private Spotify spotify;
    @Autowired
    private RabbitMQService rabbit;
    private final static String ORDERED_COLUMN_NAME = "created_date";
    private final AtomicInteger FIXED_THREAD_COUNT = new AtomicInteger(10);
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final Queue<Accounts> accountQueue = new LinkedBlockingDeque<>();

    @GetMapping(value = "/tasks/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTasks(DataTableBody body){
        int pageSize = body.getStart() / body.getLength();

        logger.info("getAllUsers() called - start: {}, length: {}, draw:{}",body.getStart(),body.getLength(),body.getDraw());
        Pageable pageableElement = PageRequest.of(pageSize, body.getLength());

        int count = tasksRepository.findAll().size();
        List<Tasks> mappedData = tasksRepository.findAllByIdIsNotNull(pageableElement);
        DataTableModel<Tasks> dataTableModel = new DataTableModel<>(mappedData,pageSize,count);
        String dtResponse = new Gson().toJson(dataTableModel);
        return ResponseEntity.ok(dtResponse);
    }

    @PostMapping(value = "/task/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTask(Tasks tasks){
        try{
            logger.info("addTask() - task url: {} ", tasks.getTaskUrl());

            tasks.setTaskAddedDate(new Date());
            tasks.setTaskLastUpdateDate(new Date());
            tasksRepository.save(tasks);

            return ResponseEntity.status(HttpStatus.OK).body(
                    Collections.singletonMap("id", tasks.getId())
            );
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonMap("id", null)
            );
        }


    }

    @PostMapping(value = "/task/run",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> runTask(String id) {
        AtomicBoolean taskStatus = new AtomicBoolean(false);

        Tasks tasks = tasksRepository.findTaskByIdIsAndTaskStatusIs(id, true);
        if (tasks == null){
            return new ResponseEntity<>(new BaseResponse(false, "Task not found."),
                    HttpStatus.BAD_REQUEST);
        }
        Constants.SPOTIFY_REMOTE_OR_LOCAL taskRemoteLocal = getTaskType(tasks.getTaskType());


        if (taskRemoteLocal == Constants.SPOTIFY_REMOTE_OR_LOCAL.LOCAL_TASK){

            int loadCount = (int) ((int) Math.max(tasks.getTaskCount(), spotify.getSessionCount()) - Math.min(tasks.getTaskCount(), spotify.getSessionCount()));

            if (loadCount == 0){
                loadCount = spotify.getSessionCount();
            }

            logger.info("runTask() - thread count: {}",loadCount);
            Pageable topTwenty = PageRequest.of(0, loadCount, Sort.Direction.DESC,ORDERED_COLUMN_NAME);
            List<Accounts> accounts = accountsRepository.findAllByIdIsNotNull(topTwenty);
            logger.info("runTask() - {} task id, accounts size is: {}",tasks.getId(),accounts.size());

            accountQueue.addAll(accounts);
            LocalTaskExecutor localTaskExecutor = new LocalTaskExecutor(tasks,accountsService);
            int threadCount = Math.min(accounts.size(), FIXED_THREAD_COUNT.get());


            for (int currentCount = 0; currentCount < threadCount;currentCount++){
                Runnable runnable = () -> {
                    while(!accountQueue.isEmpty()){
                        logger.info("{} - task is local task.",tasks.getId());
                        boolean taskResult = false;
                        try {
                            taskResult = localTaskExecutor.startTask(accountQueue.poll(),tasks.getTaskUrl(),tasks.getTaskType());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        taskStatus.set(taskResult);
                    }
                };

                logger.info("runTask() - taskStatus is: {}", taskStatus.get());

                Thread thread = new Thread(runnable);
                thread.setName("social-thread-"+currentCount);
                thread.start();
            }
        }else{
            boolean taskResult = rabbit.send(tasks);
            taskStatus.set(taskResult);
        }

        logger.info("{} - task result is {}",tasks.getId(),taskStatus.get());

        if (taskStatus.get()){
            tasks.setTaskStatus(false);
            tasksRepository.save(tasks);
            return new ResponseEntity<>(new BaseResponse(true, "Task has been started."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponse(false, "Something went wrong."),
                HttpStatus.BAD_REQUEST);

    }


}
