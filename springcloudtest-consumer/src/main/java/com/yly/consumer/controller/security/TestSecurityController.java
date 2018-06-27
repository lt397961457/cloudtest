package com.yly.consumer.controller.security;

import com.yly.consumer.entity.SecurityUser;
import com.yly.consumer.exception.UserException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/security")
public class TestSecurityController {
    @PostMapping("/first")
    public String test1(@Valid @RequestBody SecurityUser user,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));  //JDK8的新语法 Stream 和lamda 表达式。
        }
        return "test1";
    }
    @PostMapping("/first2")
    public String test1_2(@Valid @RequestBody SecurityUser user){
        return "test1_2";
    }

    @GetMapping("/secend")
    public String test2(){
        return "test2";
    }

    @GetMapping("/500error")
    public String test3(){

        int result = 1/0; //测试500错误页面
        return "test3";
    }

    //测试 @ControllerAdvice
    @GetMapping("/500errorByUserExceptionAdvice")
    public String test4(){
        try {
            int result = 1/0; //ControllerAdvice
        }catch (Exception e){
            UserException userException = new UserException();
            userException.setInfo("异常错误");
            userException.setMsg("除数为0");
            throw userException;
        }

        return "test3";
    }
}
