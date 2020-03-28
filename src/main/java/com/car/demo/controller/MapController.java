package com.car.demo.controller;

import com.car.demo.dto.AllPositionDTO;
import com.car.demo.model.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MapController {
    @ResponseBody
    @GetMapping("/positions")
    public Object getPositions(){
        List<AllPositionDTO> a=new ArrayList<>();
        a.add(new AllPositionDTO(114.335230,30.511431));
        a.add(new AllPositionDTO(114.335430,30.511531));
        return a;
    }
    @GetMapping("/map")
    public String map(){
        return "map";
    }
}
