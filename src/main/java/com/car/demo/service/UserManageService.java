package com.car.demo.service;

import com.car.demo.dto.ResultReturnDTO;
import com.car.demo.dto.UserDTO;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManageService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConvertService convertService;
    public List<UserDTO> findAll(int index,int size){
        List<User> users=userMapper.findAll(index,size);
        List<UserDTO> userDTOS =new ArrayList<>();
        for(User item:users){
            userDTOS.add(convertService.userToUserDTO(item));
        }
        return userDTOS;
    }
    public ResultReturnDTO updateOrInsertById(UserDTO userDTO){
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGrade(userDTO.getGradeName().equals("用户")?0:1);
        user.setGmtModified(new Timestamp(System.currentTimeMillis()));
        if(userDTO.getId()==null){
            //insert
            user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userMapper.insert(user);
            return new ResultReturnDTO(1,"添加成功!");
        }else {
            //update
            user.setId(userDTO.getId());
            userMapper.updateById(user);
            return new ResultReturnDTO(1,"修改成功!");
        }
    }
    public ResultReturnDTO deleteById(Long id){
        userMapper.deleteById(id);
        return new ResultReturnDTO(1,"删除成功!");
    }
}
