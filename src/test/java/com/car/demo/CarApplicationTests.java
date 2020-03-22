package com.car.demo;

import com.car.demo.controller.AdviceService;
import com.car.demo.controller.AdviceServiceImpl;
import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
@SpringBootTest
class CarApplicationTests {
    @Autowired
    private PasswordCodeService passwordCodeService;
    @Autowired
    private AdviceService adviceService;
    @Test
    public void test(){
        String user = adviceService.findUser();
        System.out.println("<><><><><><><><><><><><><>");
        adviceService.addUser();
    }
}
