package com.car.demo;

import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarApplicationTests {
    @Autowired
    private PasswordCodeService passwordCodeService;

    @Test
    void contextLoads() {
        String a=passwordCodeService.encode("dfsa51565");
        System.out.println(a);
        System.out.println(passwordCodeService.decode(a));
    }

}
