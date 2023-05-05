package com.magnakod.web;

import com.magnakod.entity.Servers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServersController {
    @GetMapping(value = "/servers")
    public String servers(){
        return "servers";
    }

    @GetMapping(value="/servers/create")
    public String serverCreate(Model model){
        model.addAttribute("serverStatus", Servers.SERVER_STATUS.values());
        return "server_create";
    }

}
