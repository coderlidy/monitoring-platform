package com.car.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {
    @GetMapping("/log")
    public String LogList(){

        return "log";
    }
}
