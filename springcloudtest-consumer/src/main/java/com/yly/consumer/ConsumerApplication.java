package com.yly.consumer;

import com.netflix.loadbalancer.RandomRule;
import com.yly.consumer.ZuulFilter.FirstZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by Administrator on 2017/12/14.
 */
@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard  //服务器性能监控
@EnableCircuitBreaker //服务器性能监控
@EnableZuulProxy //zuul
@RibbonClient(value="springcloudtest-producer",configuration=RandomRule.class) //Ribbon 负载均衡 随机模式 ；默认是轮询
@EnableSwagger2 //允许swagger2 生成RestAPI
public class ConsumerApplication {

    @Bean
    public Docket createRestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")// API 标题
                .description("rdcloud-jpa提供的RESTful APIs")// API描述
                .contact("litao")// 联系人
                .version("1.0")// 版本号
                .build();
    }

    /**
     * 注意：只会过滤Zuul 网关转发的请求，其他非Zuul转发的网关请求不会通过Zuul的过滤器
     * @return
     */
    @Bean
    public FirstZuulFilter firstZuulFilter(){
        return  new FirstZuulFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
