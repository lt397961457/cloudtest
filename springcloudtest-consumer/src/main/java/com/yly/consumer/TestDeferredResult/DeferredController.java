package com.yly.consumer.TestDeferredResult;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
@RestController
public class DeferredController {
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public DeferredResult<String> oreder() throws Exception{
        System.out.println("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber );
        DeferredResult<String> result = new  DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber ,result );
        System.out.println("主线程结束");
        return result ; //result 在tomcat中会监听到有值才返回
    }
}
