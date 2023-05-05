package com.magnakod.controller;

import com.google.gson.Gson;
import com.magnakod.entity.Servers;
import com.magnakod.mapper.DataTableBody;
import com.magnakod.mapper.DataTableModel;
import com.magnakod.repository.ServersRepository;
import com.magnakod.responses.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ServerController {
    @Autowired
    private ServersRepository serversRepository;
    private final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @GetMapping(value = "/servers/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServers(DataTableBody body){
        int pageSize = body.getStart() / body.getLength();

        logger.info("getAllUsers() called - start: {}, length: {}, draw:{}",body.getStart(),body.getLength(),body.getDraw());
        Pageable pageableElement = PageRequest.of(pageSize, body.getLength());

        int count = serversRepository.findAll().size();
        List<Servers> mappedData = serversRepository.findAllByIdIsNotNull(pageableElement);
        DataTableModel<Servers> dataTableModel = new DataTableModel<>(mappedData,pageSize,count);
        String dtResponse = new Gson().toJson(dataTableModel);
        return ResponseEntity.ok(dtResponse);
    }

    @PostMapping(value = "/servers/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateServers(String id){
        Servers serverResponse = serversRepository.getServersByIdIs(id);
        if (serverResponse == null){
            return new ResponseEntity<>(new BaseResponse(false, "Got an error."),
                    HttpStatus.BAD_REQUEST);
        }

        Servers.SERVER_STATUS serverStatus = serverResponse.getServerStatus() == Servers.SERVER_STATUS.ACTIVE
                ? Servers.SERVER_STATUS.DISABLED : Servers.SERVER_STATUS.ACTIVE;

        serverResponse.setServerStatus(serverStatus);
        serversRepository.save(serverResponse);

        return new ResponseEntity<>(new BaseResponse(true, "Update successfully."),
                HttpStatus.OK);
    }
}
