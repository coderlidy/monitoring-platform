package com.car.demo;

import com.car.demo.mapper.CraneMapper;
import com.car.demo.model.Crane;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Random;

@SpringBootTest
public class InserCrane {
    @Autowired
    private CraneMapper craneMapper;

    @Test
    public void insertCrane(Long username,Long carNumber){
        Crane crane=new Crane();
        Random random=new Random();

        int r=random.nextInt(10);
        crane.setGmtCreate(new Timestamp(System.currentTimeMillis()+r*100000000));
        crane.setGmtModified(new Timestamp(System.currentTimeMillis()+r*100000000+10000));
        crane.setUsername(username);
        crane.setCarNumber(carNumber);
        crane.setCarTypeNumber("C999"+String.valueOf(r));
        crane.setBirthday(new Timestamp(System.currentTimeMillis()-(r+1)*100000000));
        crane.setUseDay(new Timestamp(System.currentTimeMillis()+r*100000000));
        crane.setNowWeightCount(Double.valueOf(50+r));
        crane.setMaxWeightCount(Double.valueOf(100));
        crane.setMaxLiftWeight(1.6);
        craneMapper.insert(crane);
    }

    @Test
    public void insertAll(){
        //插入i个用户
        for (int i=0;i<50;i++){
            insertCrane(12345678901L+i,10001L+i);
        }
    }

}
