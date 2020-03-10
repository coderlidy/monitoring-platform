package com.car.demo.controller;

import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class indexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(){
        List<User> h1=userMapper.findAll();
        System.out.println("indexxxxxxxxxxxxx");
        if(h1==null){
            System.out.println("nulllllllllllllllllll");
        }else {
            for(User item:h1){
                System.out.println(item.getUsername());
            }
        }
        User h2=userMapper.findByUsername(123L);
        if(h2==null){
            System.out.println("nulllllllllllllllllll");
        }else {
            System.out.println(h2.getName());
        }
        return "index";
    }
}
