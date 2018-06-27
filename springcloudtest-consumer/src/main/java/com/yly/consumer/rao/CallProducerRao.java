package com.yly.consumer.rao;

import com.yly.consumer.controller.CallProducerFallBack.ProducerFallBack;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/12/14.
 */
@FeignClient(name="springcloudtest-producer",fallback = ProducerFallBack.class/* ,fallbackFactory =xxx.class 不要与fallback同时使用，功能类似范围更宽 */ ) //Hystrix 基于FeignClient使用
public interface CallProducerRao {
    @RequestMapping(value = "/getHi")
    public String getProducerHi(@RequestParam(value="name") String name);
}
