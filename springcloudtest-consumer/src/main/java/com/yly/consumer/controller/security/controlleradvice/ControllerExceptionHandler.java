package com.yly.consumer.controller.security.controlleradvice;

import com.yly.consumer.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> userExceptionHandler(UserException e){
        Map<String,Object> result = new HashMap<>();
        result.put("info",e.getInfo());
        result.put("msg","UserException JSONINFO:"+e.getMsg());
        return result;
    }
}
