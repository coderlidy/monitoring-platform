package com.car.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeiboController {
    @GetMapping("weibo")
    public String weibo(@RequestParam(name="search",defaultValue = "")String search){

        return "weibo";
    }
}
