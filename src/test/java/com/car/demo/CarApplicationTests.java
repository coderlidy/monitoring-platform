package com.car.demo;

import com.alibaba.fastjson.JSON;
import com.car.demo.model.Position;
import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@SpringBootTest
class CarApplicationTests {
    @Autowired
    private PasswordCodeService passwordCodeService;
    @Test
    public void test(){
        Stack<Position> positions=new Stack<>();
        positions.push(new Position(30.511431,114.335230));
        positions.push(new Position(30.512032,114.334051));
        positions.push(new Position(30.511714,114.333000));
        positions.push(new Position(30.510180,114.332592));
        positions.push(new Position(30.509459,114.332464));
        positions.push(new Position(30.509453,114.331571));
        positions.push(new Position(30.509271,114.333410));
        positions.push(new Position(30.508372,114.333044));
        positions.push(new Position(30.509841,114.335841));
        String str=JSON.toJSONString(positions);
        System.out.println(str);
        List<Position> b=JSON.parseArray(str,Position.class);
        System.out.println(b.get(0).getLongitude());
    }
}
