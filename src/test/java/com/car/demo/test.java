package com.car.demo;

import com.car.demo.service.PasswordCodeService;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

public class test {
    @Test
    public void m(){
        ArrayList<Integer> integerArrayList=new ArrayList<>();
        integerArrayList.add(3);
        ArrayList<String> stringArrayList=new ArrayList<>();
        stringArrayList.add("ds");
        t(integerArrayList);
        t(stringArrayList);
        Class in=integerArrayList.getClass();
        Class st=stringArrayList.getClass();
        System.out.println(in);
    }
    public static <e> void t(ArrayList<e> list){

    }
    @Test
    public void de(){
        PasswordCodeService pa=new PasswordCodeService();
        String text="-&%^$#@*()0123abcdefgz";
        System.out.println("原生："+text);
        System.out.println("加密："+pa.encode(text));
        System.out.println("解密："+pa.decode(pa.encode(text)));
    }
}
