package com.magnakod.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @GetMapping(value = "/users")
    public String users() {
        return "users";
    }

    @GetMapping(value="/users/create")
    public String usersCreate(Model model){
        int cores = Runtime.getRuntime().availableProcessors();
        model.addAttribute("core",cores);
        return "users_create";
    }
}
