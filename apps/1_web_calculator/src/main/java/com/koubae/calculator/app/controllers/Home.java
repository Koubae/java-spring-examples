package com.koubae.calculator.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class Home {
    final private String PAGE_NAME = "Home";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("PAGE_NAME", PAGE_NAME);
        return "index";
    }

}
