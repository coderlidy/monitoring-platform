package com.car.demo;

import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class testMysql {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testFindAll(){
        List<User> hh=userMapper.findAll();
        System.out.println(hh);
    }
}
