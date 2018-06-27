package com.yly.consumer.TestDeferredResult;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeferredResultHolder{
    //订单处理结果集合<订单号，处理结果>
    private Map<String , DeferredResult<String>> map = new HashMap<String , DeferredResult<String>>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}