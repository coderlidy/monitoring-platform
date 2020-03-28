package com.car.demo.wx.controller;

import com.car.demo.dto.CranenowDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.mapper.CranenowMapper;
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
    @Autowired
    private CraneMapper craneMapper;
    @GetMapping("/wx/myInfo/{username}")
    @ResponseBody
    public Object myInfo(@PathVariable("username") Long username){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        if(username!=null){
            return myCraneInfo.findUserCraneByUsername(username);
        }
        return null;
    }
    @GetMapping("/wx/cranenow/{username}")
    @ResponseBody
    public Object cranenow(@PathVariable("username") Long username){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        if(username!=null){
            CranenowDTO cranenowDTO=myCraneInfo.findCranenowByUsername(username);
            cranenowDTO.setMaxLiftWeight(craneMapper.findMaxWeightCountByUsername(username));
            return cranenowDTO;
        }
        return null;
    }
}
