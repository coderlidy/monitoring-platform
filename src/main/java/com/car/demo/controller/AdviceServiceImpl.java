package com.car.demo.controller;

public class AdviceServiceImpl implements AdviceService{
    private String name;

    public String findUser() {
        System.out.println("***************执行业务方法findUser,查找的用户名字为:"+name+"****************");
        return name;
    }

    public void addUser() {
        System.out.println("***************执行业务方法addUser****************");
        throw new RuntimeException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
