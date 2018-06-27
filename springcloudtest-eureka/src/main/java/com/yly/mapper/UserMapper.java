package com.yly.mapper;

import com.yly.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public interface UserMapper {
    List<User> getAll();
    User getOne(Long id);
    void insert(User user);

    void update(User user);

    void delete(Long id);
}
