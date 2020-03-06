package com.car.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
    @PostMapping("/admin")
    public String PostAdmin(@RequestParam(value = "inputPhone",required = false)Long username,
                          @RequestParam(value = "inputPassword",required = false)String password){
        System.out.println("111111111111111111111");
        if(username==123456 && password.equals("123")){
            System.out.println("111111111111111111111");
            return "admin";
        }else {
            return "admin";
        }
    }
    @GetMapping("/admin")
    public String UserLogin(Model model){
        System.out.println("---------gggggggggggg");
        return "admin";
    }

}
