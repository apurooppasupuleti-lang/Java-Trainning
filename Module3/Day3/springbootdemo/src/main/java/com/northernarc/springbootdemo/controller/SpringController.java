package com.northernarc.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SpringController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Apuroop";
    }

    @RequestMapping("")
    public String welcome() {
        return "Welcome to the Spring Boot application!";
    }

    @RequestMapping("/bye")
    public String bye() {
        return "Goodbye Apuroop";
    }
}
