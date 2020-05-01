package com.car.demo.advice.exception;

public enum ExceptionMsg implements IMyException {
    ERROR(2000,"未知错误"),
    CANT_FIND_INFO(2001,"未找到信息"),
    OPERATION_FALSE(2002,"操作失败"),
    Page_OUT_INDEX(2003,"页数超出索引"),
    ;


    private Integer code;
    private String msg;

     ExceptionMsg(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
