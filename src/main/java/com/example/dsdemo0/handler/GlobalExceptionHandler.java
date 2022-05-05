package com.example.dsdemo0.handler;

import com.example.dsdemo0.entity.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo exceptionHandler(Exception e) {
        ResultInfo resultInfo=new ResultInfo<>();
        resultInfo.setEntity(null);
        resultInfo.setMessage(e.getMessage());
        resultInfo.setSuccess(false);
        return resultInfo;
    }
}