package com.yly.consumer.TestDeferredResult;

import org.springframework.stereotype.Component;

@Component
public class MockQueue{
    //下单请求
    private String placeOrder;
    //订单处理完成请求
    private String comleteOrder;

    public void setPlaceOrder(String placeOrder){
                new Thread(() -> {
                    System.out.println("接到下单请求," + placeOrder);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.comleteOrder = placeOrder;
                    System.out.println("下单请求,处理完毕" + placeOrder);
                }
            ).start();
    }

    public String getPlaceOrder() {
        return placeOrder;
    }

    public String getComleteOrder() {
        return comleteOrder;
    }

    public void setComleteOrder(String comleteOrder) {
        this.comleteOrder = comleteOrder;
    }
}