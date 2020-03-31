package com.car.demo.wx.controller;

import com.car.demo.dto.CranenowDTO;
import com.car.demo.dto.LoginDTO;
import com.car.demo.dto.ResultReturnDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.mapper.UserMapper;
import com.car.demo.service.PasswordCodeService;
import com.car.demo.wx.service.MyCraneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyCraneInfoController {
    @Autowired
    private PasswordCodeService passwordCodeService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MyCraneInfo myCraneInfo;
    @Autowired
    private CraneMapper craneMapper;
    @GetMapping("/wx/myInfo/{username}")
    @ResponseBody
    public Object myInfo(@PathVariable("username") Long username, HttpServletResponse response){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        if(username!=null){

            return myCraneInfo.findUserCraneByUsername(username);
        }
        return null;
    }
    @GetMapping("/wx/cranenow/{username}")
    @ResponseBody
    public Object cranenow(@PathVariable("username") Long username, HttpServletResponse response){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        if(username!=null){
            CranenowDTO cranenowDTO=myCraneInfo.findCranenowByUsername(username);
            cranenowDTO.setMaxLiftWeight(craneMapper.findMaxWeightCountByUsername(username));
            return cranenowDTO;
        }
        return null;
    }
    @PostMapping("/wx/loginStatus")//登录状态判断
    @ResponseBody
    public Object loginStatus(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        if(userMapper.findPasswordByUsername(loginDTO.getUsername()).equals(passwordCodeService.decode(loginDTO.getPassword()))){
            return new ResultReturnDTO(1,"登录成功!");
        }else {
            return new ResultReturnDTO(0,"手机号或密码错误!");
        }

    }
}
