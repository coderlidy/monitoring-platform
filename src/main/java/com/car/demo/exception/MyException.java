package com.car.demo.exception;

public class MyException extends RuntimeException {
    private Integer code;
    private String message;
    public MyException(IMyException ex){
        this.code=ex.getCode();
        this.message=ex.getMsg();
    }
    @Override
    public String getMessage() {
        return this.message;
    }
    public Integer getCode(){
        return this.code;
    }
}
