package com.car.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    @GetMapping("/equipment")
    public String EquipmentList(){

        return "equipment";
    }
}
