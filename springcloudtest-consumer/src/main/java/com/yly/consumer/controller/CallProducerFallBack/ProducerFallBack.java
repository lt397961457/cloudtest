package com.yly.consumer.controller.CallProducerFallBack;

import com.yly.consumer.rao.CallProducerRao;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/12/15.
 */
@Component
public class ProducerFallBack implements CallProducerRao {
    @Override
    public String getProducerHi(@RequestParam(value = "name") String name) {
        return "FallBack in Consumer --->" + name;
    }
}
