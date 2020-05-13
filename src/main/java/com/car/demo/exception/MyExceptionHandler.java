package com.car.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler()
    public String dealException(Model model,Exception ex){
        System.out.println(ex.getMessage());
        if(ex instanceof MyException){
            model.addAttribute("errorMsg",ex.getMessage());
        }else {
            ex.printStackTrace();
        }
        return "error";
    }
}
