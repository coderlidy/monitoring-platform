package com.car.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}
