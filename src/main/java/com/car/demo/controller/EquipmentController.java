package com.car.demo.controller;

import com.car.demo.dto.CraneDTO;
import com.car.demo.dto.CraneDTO;
import com.car.demo.service.CraneManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EquipmentController {
    @Autowired
    private CraneManageService craneManageService;
    @GetMapping("/equipment")
    public String EquipmentList(Model model){
        model.addAttribute("craneDTOList",craneManageService.findAll());
        return "equipment";
    }
    @ResponseBody
    @PostMapping("/crane/update")
    public Object updateCrane(@RequestBody CraneDTO craneDTO){
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return craneManageService.updateOrInsertById(craneDTO);
    }
    @ResponseBody
    @PostMapping("/crane/insert")
    public Object insertUser(@RequestBody CraneDTO craneDTO){
        System.out.println(craneDTO.getId());
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return craneManageService.updateOrInsertById(craneDTO);
    }
    @ResponseBody
    @PostMapping("/crane/delete")
    public Object deleteUser(@RequestBody CraneDTO craneDTO){
        System.out.println(craneDTO.getId());
        System.out.println(this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());
        return craneManageService.deleteById(craneDTO.getId());
    }
}
