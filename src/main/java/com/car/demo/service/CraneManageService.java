package com.car.demo.service;

import com.car.demo.dto.CraneDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.model.Crane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CraneManageService {
    @Autowired
    private CraneMapper craneMapper;
    public List<CraneDTO> findAll(){
        List<Crane> craneList=craneMapper.findAll();
        List<CraneDTO> craneDTOList=new ArrayList<>();
        for (Crane item:craneList){
            CraneDTO craneDTO=new CraneDTO();
            craneDTO.setCarNumber(item.getCarNumber());
            craneDTO.setMaxLiftWeight(item.getMaxLiftWeight());
            craneDTO.setNowWeightCount(item.getNowWeightCount());
            craneDTO.setUsername(item.getUsername());
            craneDTO.setMaxWeightCount(item.getMaxWeightCount());
            craneDTO.setCarTypeNumber(item.getCarTypeNumber());
            craneDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getBirthday()));
            craneDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtCreate()));
            craneDTO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtModified()));
            craneDTO.setUseDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getUseDay()));
            craneDTOList.add(craneDTO);
        }
        return craneDTOList;
    }

}
