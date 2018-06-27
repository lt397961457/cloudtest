package com.yly.consumer.config;

import com.yly.consumer.ZuulFilter.FirstZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/1/3.
 */
//@Configuration
public class ZuulConfig {
    @Bean
    public FirstZuulFilter firstZuulFilter(){
        return  new FirstZuulFilter();
    }
}
