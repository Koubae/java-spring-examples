package com.koubae.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ControllerHome {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "<h1>Welcome</h1><p>TODO - App</p>";
    }
}
