package com.car.demo;

import com.alibaba.fastjson.JSON;
import com.car.demo.mapper.CranenowMapper;
import com.car.demo.mapper.LogMapper;
import com.car.demo.model.Cranenow;
import com.car.demo.model.Log;
import com.car.demo.model.Position;
import com.car.demo.redis.MyRedisUtils;
import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarApplicationTests {
    @Autowired
    private LogMapper logMapper;
    @Transactional()
    @Test
    public void test(){
        Log log=new Log();
        log.setIp("hhh");
        log.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        //System.out.println(logMapper.insert(log));
        int i=1/1;
        //int i=1/0;
    }

    @Autowired
    private MyRedisUtils redisUtils;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key", "redis_vale");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }
}
