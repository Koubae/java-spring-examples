package com.koubae.todolist.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {
    @GetMapping("/")
    @ResponseBody
    String home() {
        return "<h1>Welcome</h1>";
    }
}
