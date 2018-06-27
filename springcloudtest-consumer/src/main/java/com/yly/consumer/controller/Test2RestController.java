package com.yly.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/14.
 */
@RestController("/dddd") //后面不能加路径？
public class Test2RestController {

//    @Autowired
//    CallProducerRao producerRao;
//
//    @RequestMapping("/HiForProducer/{name}")
//    public String getProucerHi(@PathVariable("name") String name){
//        return producerRao.getProducerHi(name);
//    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test(){
        return "test success";
    }
}
