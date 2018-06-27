package com.yly.consumer.TestDeferredResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//消息队列监听器：监听MockQueue 中 comleteOrder 有值
@Component
//ContextRefreshedEvent 整个Spring容器初始完成事件
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getComleteOrder())) {
                    String orderNumber = mockQueue.getComleteOrder();
                    System.out.println("返回订单处理结果，" + orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place Order success");
                    mockQueue.setComleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}