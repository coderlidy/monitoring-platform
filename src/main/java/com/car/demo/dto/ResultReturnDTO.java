package com.car.demo.dto;

public class ResultReturnDTO {
    private int ifSuccess;//1成功2失败
    private String message;
    public ResultReturnDTO(int ifSuccess, String message){
        this.ifSuccess=ifSuccess;
        this.message=message;
    }
    public int getIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(int ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
