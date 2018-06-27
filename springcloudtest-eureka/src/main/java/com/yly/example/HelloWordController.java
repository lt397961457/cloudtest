package com.yly.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



/**
 * Created by Administrator on 2017/11/27.
 */
@SpringBootApplication(scanBasePackages = "com.yly")
@ServletComponentScan(basePackages = "com.yly") //适用@WebFilter 时候必须开启此注解
@MapperScan("com.yly.mapper")
@EnableEurekaServer //SpringCloud
public class HelloWordController {

//    @Bean
//    public FilterRegistrationBean testFilterRegistration(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new MyFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.addInitParameter("testname", "testvalue");
//        registrationBean.setOrder(1);
//        registrationBean.setName("filter1");
//
//        return registrationBean;
//    }
//    @Bean
//    public FilterRegistrationBean test2FilterRegistration(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new MyFilter2());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.addInitParameter("testname2", "testvalue2");
//        registrationBean.setOrder(2);
//        registrationBean.setName("filter2");
//
//        return registrationBean;
//    }


    public static void main(String[] args) {
        SpringApplication.run(HelloWordController.class,args);
    }
}
