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
@SpringBootTest
public class InsertCranenow {
    @Autowired
    private PasswordCodeService passwordCodeService;
    @Autowired
    private CranenowMapper cranenowMapper;
    @Test
    public void insertCranenow(){
        Cranenow cranenow=new Cranenow();
        DecimalFormat df = new DecimalFormat("#.000000");
        Random random=new Random();
        List<Position> positionList=new ArrayList<>();
        for (int i=0;i<10;i++){
            String lon=df.format(114.331+random.nextInt(400)/100000.0);
            String lat=df.format(30.508+random.nextInt(400)/100000.0);
            positionList.add(new Position(lon,lat));
        }
        int r=random.nextInt(10);
        System.out.println(new Timestamp(System.currentTimeMillis()+r*100000000));
        System.out.println(new Timestamp(System.currentTimeMillis()+r*100000000+10000));
        String po= JSON.toJSONString(positionList);
        cranenow.setPositions(po);
        cranenow.setCarNumber(Long.valueOf(10001+random.nextInt(50)));
        cranenow.setNowWeight(Double.valueOf(7.6+random.nextInt(10)));
        cranenow.setGmtCreate(new Timestamp(System.currentTimeMillis()+r*100000000));
        cranenow.setGmtModified(new Timestamp(System.currentTimeMillis()+r*100000000+10000));
        cranenowMapper.insert(cranenow);
    }

    @Test//插入100个随机cranenow
    public void insertAll(){
        for (int i=0;i<200;i++){
            insertCranenow();
        }
    }
}
