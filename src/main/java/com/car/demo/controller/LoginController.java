package com.car.demo.controller;

import com.car.demo.dto.LoginDTO;
import com.car.demo.dto.ResultReturnDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.service.PasswordCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordCodeService passwordCodeService;
    @PostMapping("/login")
    @ResponseBody
    private Object login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        if(userMapper.findPasswordByUsername(loginDTO.getUsername()).equals(loginDTO.getPassword())){
            //添加加密cookie
            response.addCookie(new Cookie("loginPassword",passwordCodeService.encode(loginDTO.getPassword())));
            return new ResultReturnDTO(1,"登录成功!");
        }else {
            return new ResultReturnDTO(0,"手机号或密码错误!");
        }
    }
}
