package com.car.demo.controller;

import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/first")
    public String first(){
        return "first";
    }
    @GetMapping("ws")
    public String page(){
        return "websocket";
    }
}
