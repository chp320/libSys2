package com.example.back_end.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/connectTest")
    public String Test() {
        return "Spring-Boot and Vue connect test.";
    }
}
