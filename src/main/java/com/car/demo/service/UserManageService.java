package com.car.demo.service;

import com.car.demo.dto.UserInfoDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManageService {
    @Autowired
    private UserMapper userMapper;
    public List<UserInfoDTO> findAll(){
        List<User> users=userMapper.findAll();
        List<UserInfoDTO> userInfoDTOS=new ArrayList<>();
        for(User item:users){
            UserInfoDTO userInfoDTO=new UserInfoDTO();
            userInfoDTO.setAge(item.getAge());
            userInfoDTO.setUsername(item.getUsername());
            userInfoDTO.setGradeName(item.getGrade()==1?"用户":"管理员");
            userInfoDTO.setName(item.getName());
            userInfoDTO.setPassword(item.getPassword());
            // Timestamp -> String
            userInfoDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtCreate()));
            userInfoDTO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtModified()));
            userInfoDTOS.add(userInfoDTO);
        }
        return userInfoDTOS;
    }
}
