package com.yly.consumer.entity;

/**
 * Created by Administrator on 2017/12/21.
 */

/**
 * 用于Ehcache缓存测试用
 */
public class Product {
    private int id;
    private String name;
    private Long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
