package com.magnakod.controller;

import com.magnakod.entity.Settings;
import com.magnakod.mapper.DataTableBody;
import com.magnakod.repository.SettingsRepository;
import com.magnakod.responses.BaseResponse;
import com.magnakod.service.settings.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class SettingController {
    @Autowired
    private SettingsService settingsService;

    @PostMapping(value = "/proxies/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateProxies(String proxyLists) {
        if (proxyLists != null){
            settingsService.updateSettings(proxyLists);
            return new ResponseEntity<>(new BaseResponse(true, "Proxies updated."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponse(false, "Something went wrong."),
                HttpStatus.BAD_REQUEST);
    }
}
