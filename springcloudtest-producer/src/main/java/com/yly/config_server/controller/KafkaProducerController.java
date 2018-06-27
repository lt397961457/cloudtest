package com.yly.config_server.controller;

import com.yly.config_server.kafkaproducer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/27.
 */
@RestController
public class KafkaProducerController {
    @Autowired
    KafkaProducer kafkaProducer;
    @GetMapping("/kafka/send")
    public void send(@RequestParam String msg){

        kafkaProducer.send(msg);
    }
}
