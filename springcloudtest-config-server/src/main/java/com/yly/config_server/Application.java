package com.yly.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by Administrator on 2017/12/14.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer //启用SpringConfig
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
