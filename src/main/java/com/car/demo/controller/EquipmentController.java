package com.car.demo.controller;

import com.car.demo.dto.CraneDTO;
import com.car.demo.dto.CraneDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.service.CraneManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EquipmentController {
    @Autowired
    private CraneManageService craneManageService;
    @Autowired
    private CraneMapper craneMapper;
    @GetMapping("/equipment")
    public String EquipmentList(Model model,@RequestParam(name="page",defaultValue = "1")Integer page){
        int size=12;
        int count=craneMapper.getCraneCount();
        model.addAttribute("nowPage",page);
        //返回合理的最大页数
        model.addAttribute("maxPage",count%size==0?count/size:count/size+1);
        model.addAttribute("craneDTOList",craneManageService.findAll((page-1)*12,size));
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
