package com.car.demo.service;

import com.car.demo.dto.CraneDTO;
import com.car.demo.dto.UserDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.Crane;
import com.car.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class ConvertService {
    @Autowired
    private UserMapper userMapper;
    public CraneDTO craneToCraneDTO(Crane crane){
        if(crane==null)return null;
        CraneDTO craneDTO=new CraneDTO();
        craneDTO.setId(crane.getId());
        craneDTO.setCarNumber(crane.getCarNumber());
        craneDTO.setMaxLiftWeight(crane.getMaxLiftWeight());
        craneDTO.setNowWeightCount(crane.getNowWeightCount());
        craneDTO.setUsername(crane.getUsername());
        craneDTO.setName(userMapper.findNameByUsername(crane.getUsername()));
        craneDTO.setMaxWeightCount(crane.getMaxWeightCount());
        craneDTO.setCarTypeNumber(crane.getCarTypeNumber());
        craneDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(crane.getBirthday()));
        craneDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(crane.getGmtCreate()));
        craneDTO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(crane.getGmtModified()));
        craneDTO.setUseDay(new SimpleDateFormat("yyyy-MM-dd").format(crane.getUseDay()));
        return craneDTO;
    }
    public Crane craneDTOToCrane(CraneDTO craneDTO){
        return null;
    }
    public UserDTO userToUserDTO(User user){
        if(user==null)return null;
        UserDTO userDTO =new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAge(user.getAge());
        userDTO.setUsername(user.getUsername());
        userDTO.setGradeName(user.getGrade()==0?"用户":"管理员");
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        // Timestamp -> String
        userDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getGmtCreate()));
        userDTO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getGmtModified()));
        return userDTO;
    }
}
