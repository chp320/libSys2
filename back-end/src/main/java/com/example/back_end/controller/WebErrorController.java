package com.example.back_end.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebErrorController implements ErrorController {

    @GetMapping({"/", "/error"})
    public String redirectBoot() {
        return "index.html";
    }

    public String getErrorPath() {
        return "/error";
    }
}
