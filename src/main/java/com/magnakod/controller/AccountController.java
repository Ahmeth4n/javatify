package com.magnakod.controller;

import com.google.gson.Gson;
import com.magnakod.dto.AccountModel;
import com.magnakod.dto.AccountSaveModel;
import com.magnakod.mapper.DataTableModel;
import com.magnakod.entity.Accounts;
import com.magnakod.mapper.DataTableBody;
import com.magnakod.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api")
public class AccountController {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/users/add")
    public ResponseEntity<String> save(@RequestBody Accounts accounts){
        accountsRepository.save(accounts);
        AccountSaveModel accountSaveModel = new AccountSaveModel(true,accounts.getSession().getGeneratedUsername(),accounts.getCreated_date());
        String data = new Gson().toJson(accountSaveModel);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/list/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers(DataTableBody body){
        int pageSize = body.getStart() / body.getLength();

        logger.info("getAllUsers() called - start: {}, length: {}, draw:{}",body.getStart(),body.getLength(),body.getDraw());
        Pageable pageableElement = PageRequest.of(pageSize ,body.getLength());

        int count = accountsRepository.findAll().size();

        List<AccountModel> mappedData = accountsRepository.findAllByIdIsNotNull(pageableElement).stream()
                .map(post -> modelMapper.map(post, AccountModel.class))
                .collect(Collectors.toList());

        DataTableModel<AccountModel> dataTableModel = new DataTableModel<>(mappedData,mappedData.size(),count);
        String dtResponse = new Gson().toJson(dataTableModel);
        return ResponseEntity.ok(dtResponse);
    }

}
