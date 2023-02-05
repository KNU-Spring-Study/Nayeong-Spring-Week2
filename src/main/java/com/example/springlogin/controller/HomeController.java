package com.example.springlogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "홈화면입니다.";
    }
}