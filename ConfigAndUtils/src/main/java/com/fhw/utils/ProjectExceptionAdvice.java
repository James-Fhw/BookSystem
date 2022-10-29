package com.fhw.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ProjectExceptionAdvice {

    @ExceptionHandler
    public R doException(Exception ex){
        ex.printStackTrace();
        return new R(false,"服务器故障，请稍后再试");
    }
}
