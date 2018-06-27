package com.yly.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yly.consumer.rao.CallProducerRao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/14.
 */
@RestController //后面不能加路径？
public class CallProducerController {

    @Autowired
    CallProducerRao producerRao;

    @GetMapping("/HiForProducer/{name}")
    public String getProucerHi(@PathVariable("name") String name){
        return producerRao.getProducerHi(name);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test success";
    }

    // Hystirx独立于FeignClient的使用
    @RequestMapping(value = "/testHystix",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallBack1")
    public String testHystix(){
        return "test success";
    }
    public String fallBack1(){
        return "fallBack1";
    }
}
