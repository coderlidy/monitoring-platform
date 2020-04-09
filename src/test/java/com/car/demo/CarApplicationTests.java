package com.car.demo;

import com.alibaba.fastjson.JSON;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.model.Cranenow;
import com.car.demo.model.Position;
import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@SpringBootTest
class CarApplicationTests {

    @Test
    public void test(){
        System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Timestamp(System.currentTimeMillis()+100000000));
        System.out.println(System.currentTimeMillis());
    }

}
