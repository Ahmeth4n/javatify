package com.magnakod.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerReportsController {
    @GetMapping(value = "/server-reports")
    public String reports(){
        return "server_reports";
    }

}
