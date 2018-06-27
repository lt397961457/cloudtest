package com.yly.consumer.service;

import com.yly.consumer.entity.Product;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface EhcacheService {
    public Product saveCache(Product product);
    public Product getCache(int id);
    public List<Product> getListInCache();
}
