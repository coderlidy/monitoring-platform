package com.car.demo.service;

import com.car.demo.dto.CraneDTO;
import com.car.demo.dto.ResultReturnDTO;
import com.car.demo.mapper.CraneMapper;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.Crane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CraneManageService {
    @Autowired
    private CraneMapper craneMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConvertService convertService;
    public List<CraneDTO> findAll(){
        List<Crane> craneList=craneMapper.findAll();
        List<CraneDTO> craneDTOList=new ArrayList<>();
        for (Crane item:craneList){
            craneDTOList.add(convertService.craneToCraneDTO(item));
        }
        return craneDTOList;
    }
    public ResultReturnDTO updateOrInsertById(CraneDTO craneDTO){
        Crane crane=new Crane();
        crane.setUsername(craneDTO.getUsername());
        crane.setBirthday(Timestamp.valueOf(craneDTO.getBirthday()+" 10:30:40"));
        crane.setUseDay(Timestamp.valueOf(craneDTO.getUseDay()+" 10:30:40"));
        crane.setCarNumber(craneDTO.getCarNumber());
        crane.setCarTypeNumber(craneDTO.getCarTypeNumber());
        crane.setMaxLiftWeight(craneDTO.getMaxLiftWeight());
        crane.setNowWeightCount(craneDTO.getNowWeightCount());
        crane.setMaxWeightCount(craneDTO.getMaxWeightCount());
        crane.setGmtModified(new Timestamp(System.currentTimeMillis()));
        if(craneDTO.getId()==null){
            //insert
            crane.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            craneMapper.insert(crane);
            return new ResultReturnDTO(1,"添加成功!");
        }else {
            //update
            crane.setId(craneDTO.getId());
            craneMapper.updateById(crane);
            return new ResultReturnDTO(1,"修改成功!");
        }
    }
    public ResultReturnDTO deleteById(Long id){
        craneMapper.deleteById(id);
        return new ResultReturnDTO(1,"删除成功!");
    }
}
