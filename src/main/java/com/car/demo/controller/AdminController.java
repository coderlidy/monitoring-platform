package com.car.demo.controller;

import com.car.demo.dto.UserBoxReturnDTO;
import com.car.demo.dto.UserInfoDTO;
import com.car.demo.model.User;
import com.car.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserManageService userManageService;
//    @PostMapping("/admin")
//    public String PostAdmin(@RequestParam(value = "inputPhone",required = false)Long username,
//                          @RequestParam(value = "inputPassword",required = false)String password){
//        System.out.println("111111111111111111111");
//        if(username==123456 && password.equals("123")){
//            System.out.println("111111111111111111111");
//            return "admin";
//        }else {
//            return "admin";
//        }
//    }
    @ResponseBody
    @PostMapping("/user/update")
    public Object modifiedUser(@RequestBody UserInfoDTO userInfoDTO){
        System.out.println(userInfoDTO.getGradeName());
        UserBoxReturnDTO userBoxReturnDTO;
        userBoxReturnDTO=new UserBoxReturnDTO(1,"修改成功!");

        System.out.println(this.getClass().getName());
        return userBoxReturnDTO;
    }

    @GetMapping("/admin")
    public String UserLogin(Model model){
        model.addAttribute("userInfoDTOS",userManageService.findAll());
        System.out.println(this.getClass().getName());
        return "admin";
    }
}
