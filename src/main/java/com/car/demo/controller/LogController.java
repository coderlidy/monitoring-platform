package com.car.demo.controller;

import com.car.demo.mapper.LogMapper;
import com.car.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private LogService logService;
    @GetMapping("/log")
    public String findAllUser(Model model, @RequestParam(name="page",defaultValue = "1")Integer page){
        int size=12;
        int count=logMapper.getLogCount();
        model.addAttribute("nowPage",page);
        //返回合理的最大页数
        model.addAttribute("maxPage",count%size==0?count/size:count/size+1);
        model.addAttribute("logDTOS",logService.findAll((page-1)*12,size));
        return "log";
    }
}
