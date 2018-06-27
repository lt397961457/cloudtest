package com.yly.consumer.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/27.
 */
@Component
public class KafkaCosumer {
//    @KafkaListener(topics={"test1"})  //要使用kafaka 就取消该行注释
    public void receive(String message){
        System.out.println("topic=========test1");
        System.out.println(message);
    }
}
