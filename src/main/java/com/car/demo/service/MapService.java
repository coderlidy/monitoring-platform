package com.car.demo.service;

import com.alibaba.fastjson.JSON;
import com.car.demo.dto.AllPositionDTO;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {
    @Autowired
    private CranenowMapper cranenowMapper;
    public List<AllPositionDTO> deal(){
        List<String> stringList=cranenowMapper.findPositions();
        List<AllPositionDTO> allPositionDTOList=new ArrayList<>();
        for(String temp:stringList){
            List<Position> positionList=JSON.parseArray(temp,Position.class);
            allPositionDTOList.add(new AllPositionDTO(Double.valueOf(positionList.get(0).getLongitude()),Double.valueOf(positionList.get(0).getLatitude())));
        }
        return allPositionDTOList;
    }
}
