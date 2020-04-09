package com.car.demo.controller;

import com.car.demo.dto.UserDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private UserMapper userMapper;
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
    public String findAllUser(Model model, @RequestParam(name="page",defaultValue = "1")Integer page){
        int size=12;
        int count=userMapper.getUserCount();
        model.addAttribute("nowPage",page);
        //返回合理的最大页数
        model.addAttribute("maxPage",count%size==0?count/size:count/size+1);
        model.addAttribute("userDTOS",userManageService.findAll((page-1)*12,size));
        return "admin";
    }
}
