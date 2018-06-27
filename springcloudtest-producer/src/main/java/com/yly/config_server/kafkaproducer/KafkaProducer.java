package com.yly.config_server.kafkaproducer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/27.
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void send(String msg){
        System.out.println("Kafka Producer send data--->" + msg);
        //第一个参数是 topic 名称
        kafkaTemplate.send("test1",msg);
    }
}
