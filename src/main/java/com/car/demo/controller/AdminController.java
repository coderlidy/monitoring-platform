package com.car.demo.controller;

import com.car.demo.dto.UserDTO;
import com.car.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserManageService userManageService;
    @ResponseBody
    @PostMapping("/user/update")
    public Object updateUser(@RequestBody UserDTO userDTO){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return userManageService.updateOrInsertById(userDTO);
    }
    @ResponseBody
    @PostMapping("/user/insert")
    public Object insertUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getId());
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return userManageService.updateOrInsertById(userDTO);
    }
    @ResponseBody
    @PostMapping("/user/delete")
    public Object deleteUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getId());
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return userManageService.deleteById(userDTO.getId());
    }


    @GetMapping("/admin")
    public String findAllUser(Model model){
        model.addAttribute("userDTOS",userManageService.findAll());
        System.out.println(this.getClass().getName());
        return "admin";
    }
}
