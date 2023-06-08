package com.koubae.todolist.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koubae.todolist.app.controllers.base.BaseController;


@Controller
public class ControllerHome extends BaseController {
    @GetMapping("/")
    @ResponseBody String home() {
        return String.format("<h1>Welcome to %s</h1>", APP_NAME);
    }

    @GetMapping("/ping")
    @ResponseBody String ping() {
        return "pong";
    }

}
