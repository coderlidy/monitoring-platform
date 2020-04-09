package com.car.demo;

import com.car.demo.mapper.UserMapper;
import com.car.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Random;
@SpringBootTest
public class InserUser {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insertUser(Long username){
        User user=new User();
        Random random=new Random();

        int r=random.nextInt(10);
        System.out.println(new Timestamp(System.currentTimeMillis()+r*100000000));
        System.out.println(new Timestamp(System.currentTimeMillis()+r*100000000+10000));
        user.setGmtCreate(new Timestamp(System.currentTimeMillis()+r*100000000));
        user.setGmtModified(new Timestamp(System.currentTimeMillis()+r*100000000+10000));
        user.setUsername(username);
        user.setPassword(RandomResult.getStringRandom(6));
        user.setGrade(0);
        user.setName(RandomResult.getRandomName(true,3));
        user.setAge(random.nextInt(20)+30);
        userMapper.insert(user);
    }

    @Test
    public void insertAll(){
        //插入i个用户
        for (int i=0;i<50;i++){
            insertUser(12345678901L+i);
        }
    }

}
