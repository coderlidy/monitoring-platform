package com.car.demo.wx.controller;

import com.car.demo.wx.service.MyCraneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyCraneInfoController {
    @Autowired
    private MyCraneInfo myCraneInfo;
    @GetMapping("/wx/myInfo/{username}")
    @ResponseBody
    public Object myInfo(@PathVariable("username") Long username){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        if(username!=null){
            return myCraneInfo.findUserCraneByUsername(username);
        }
        return null;
    }
}
