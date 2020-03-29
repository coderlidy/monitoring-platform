package com.car.demo.controller;

import com.car.demo.dto.AllPositionDTO;
import com.car.demo.model.Position;
import com.car.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MapController {
    @Autowired
    private MapService mapService;
    @ResponseBody
    @GetMapping("/positions")
    public Object getPositions(){

        return mapService.deal();
    }
    @GetMapping("/map")
    public String map(){
        return "map";
    }
}
