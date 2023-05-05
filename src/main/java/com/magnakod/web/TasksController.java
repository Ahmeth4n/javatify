package com.magnakod.web;

import com.magnakod.Constants;
import com.magnakod.entity.Tasks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @GetMapping(value = "/tasks")
    public String tasks(Model model) {
        model.addAttribute("taskTypes", Constants.SPOTIFY_TASK_TYPE.values());
        return "tasks";
    }

    @GetMapping(value = "/tasks/create")
    public String tasksCreate(Model model){
        model.addAttribute("taskTypes", Constants.SPOTIFY_TASK_TYPE.values());
        return "tasks_create";
    }

}
