package com.magnakod.web;

import com.magnakod.entity.Settings;
import com.magnakod.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    public SettingsRepository settingsRepository;

    @GetMapping(value = "/settings")
    public String servers(Model model){
        String version = System.getProperty("java.version");
        Settings proxyLists = settingsRepository.findAll().get(0);

        model.addAttribute("java_version",version);
        model.addAttribute("settings",proxyLists.getProxyList());
        model.addAttribute("lastUpdateDate",proxyLists.getLastUpdateDate());
        return "settings";
    }

}
