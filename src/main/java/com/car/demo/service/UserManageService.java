package com.car.demo.service;

import com.car.demo.dto.UserBoxReturnDTO;
import com.car.demo.dto.UserInfoDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
            userInfoDTO.setId(item.getId());
            userInfoDTO.setAge(item.getAge());
            userInfoDTO.setUsername(item.getUsername());
            userInfoDTO.setGradeName(item.getGrade()==0?"用户":"管理员");
            userInfoDTO.setName(item.getName());
            userInfoDTO.setPassword(item.getPassword());
            // Timestamp -> String
            userInfoDTO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtCreate()));
            userInfoDTO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getGmtModified()));
            userInfoDTOS.add(userInfoDTO);
        }
        return userInfoDTOS;
    }
    public UserBoxReturnDTO updateOrInsertById(UserInfoDTO userInfoDTO){
        User user=new User();
        user.setUsername(userInfoDTO.getUsername());
        user.setPassword(userInfoDTO.getPassword());
        user.setName(userInfoDTO.getName());
        user.setAge(userInfoDTO.getAge());
        user.setGrade(userInfoDTO.getGradeName().equals("用户")?0:1);
        user.setGmtModified(new Timestamp(System.currentTimeMillis()));
        if(userInfoDTO.getId()==null){
            //insert
            user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userMapper.insert(user);
            return new UserBoxReturnDTO(1,"添加成功!");
        }else {
            //update
            user.setId(userInfoDTO.getId());
            userMapper.updateById(user);
            return new UserBoxReturnDTO(1,"修改成功!");
        }
    }
    public UserBoxReturnDTO deleteById(Long id){
        userMapper.deleteById(id);
        return new UserBoxReturnDTO(1,"删除成功!");
    }
}
