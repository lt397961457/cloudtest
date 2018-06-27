package com.yly.config_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/14.
 */
@RestController //后面不能加路径？
public class TestController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/getHi")
    public String test1(@RequestParam String name){
        return port+"-->hello:"+name;
    }
    @RequestMapping("/getHi2")
    public String test2(@RequestParam String name){
        return port+"-->hello2:"+name;
    }
    @RequestMapping("/level/getHi3")
    public String test3(@RequestParam String name){
        return port + "-->/level/hello3:"+name;
    }
}
