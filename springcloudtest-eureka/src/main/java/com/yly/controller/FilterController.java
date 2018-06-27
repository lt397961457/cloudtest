package com.yly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
@Controller
//@RestController //Rest风格的controller注解 默认所有请求的返回值都是JSON，所以方法是上的@ResponseBody不需要
@RequestMapping("/")
public class FilterController {
    @Autowired
    FilterRegistrationBean testFilterRegistration;

    @RequestMapping("hello")
    @ResponseBody
    public Map home(HttpServletRequest request){
        Map<String,String> servletInitPara = testFilterRegistration.getInitParameters();
        return servletInitPara;
    }
}
