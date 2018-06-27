package com.yly.consumer.controller.ehcache;

import com.yly.consumer.entity.Product;
import com.yly.consumer.service.EhcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/21.
 */

/**
 * EhCache整合测试Controller
 */
@RestController
public class EhcacheController {

    @Autowired
    private EhcacheService ehCacheService;

    @PutMapping("/put2cache")
    public void setCache(@RequestBody Product product){
        ehCacheService.saveCache(product);
    }

    @GetMapping("/get4cache")
    public Product getCache(@RequestParam("productId") Integer productId){
        return ehCacheService.getCache(productId);
    }
}
