package com.car.demo.controller;

import com.car.demo.service.CraneManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    @Autowired
    private CraneManageService craneManageService;
    @GetMapping("/equipment")
    public String EquipmentList(Model model){
        model.addAttribute("craneDTOList",craneManageService.findAll());
        System.out.println(this.getClass().getName());
        return "equipment";
    }
}
