package com.magnakod.controller;

import com.google.gson.Gson;
import com.magnakod.entity.ServerReports;
import com.magnakod.entity.Servers;
import com.magnakod.mapper.DataTableBody;
import com.magnakod.mapper.DataTableModel;
import com.magnakod.repository.ServerReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServerReportController {
    @Autowired
    private ServerReportsRepository serverReportsRepository;
    @GetMapping(value = "/serverReports/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServerReports(DataTableBody body){
        int pageSize = body.getStart() / body.getLength();
        Pageable pageableElement = PageRequest.of(pageSize, body.getLength());

        int count = serverReportsRepository.findAll().size();
        List<ServerReports> mappedData = serverReportsRepository.findAllByIdIsNotNull(pageableElement);
        DataTableModel<ServerReports> dataTableModel = new DataTableModel<>(mappedData,pageSize,count);
        String dtResponse = new Gson().toJson(dataTableModel);
        return ResponseEntity.ok(dtResponse);
    }
}
