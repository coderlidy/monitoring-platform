package com.car.demo;

import com.alibaba.fastjson.JSON;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.mapper.LogMapper;
import com.car.demo.model.Cranenow;
import com.car.demo.model.Log;
import com.car.demo.model.Position;
import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@SpringBootTest
class CarApplicationTests {
    @Autowired
    private LogMapper logMapper;
    @Transactional()
    @Test
    public void test(){
        Log log=new Log();
        log.setIp("hhh");
        log.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        System.out.println(logMapper.insert(log));
        int i=1/1;
        //int i=1/0;
    }

}
