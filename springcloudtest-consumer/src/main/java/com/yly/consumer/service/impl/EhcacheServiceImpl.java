package com.yly.consumer.service.impl;

import com.yly.consumer.entity.Product;
import com.yly.consumer.service.EhcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service("ehCacheService")
public class EhcacheServiceImpl implements EhcacheService {


    @Autowired
    EhCacheCacheManager ehCacheCacheManager;
    public static final String CACHE_NAME = "local";

    //默认：@CachePut 的方法要自带返回值，存储的是返回后的结果
    @CachePut(value=CACHE_NAME,key="'key_'+#product.getId()")
//    @Cacheable(value=CACHE_NAME,key="'key_'+#product.getId()")
    @Override
    public Product saveCache(Product product) {
        product.setName("名字被修改了！");
        return product;

//        Cache cache = ehCacheCacheManager.getCache(CACHE_NAME);
//        cache.put("key_product_"+product.getId(),product);
    }

    @Cacheable(value = CACHE_NAME,key = "'key_'+#id")
    @Override
    public Product getCache(int id) {
//        Cache cache = ehCacheCacheManager.getCache(CACHE_NAME);
//        Product product = cache.get("key_product_"+id,Product.class);
//        return product;
        return null;
    }

    @Override
    public List<Product> getListInCache() {
        Cache cache = ehCacheCacheManager.getCache(CACHE_NAME);
        return null;
    }
}
