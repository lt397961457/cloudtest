package com.yly.service;

import com.yly.entity.User;
import com.yly.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@Service
@CacheConfig(cacheNames = "users",keyGenerator="keyGenerator")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    @Cacheable
    public User getOne(Long id) {
        System.out.print("进入数据库查询");
        return userMapper.getOne(id);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    @CachePut
    public void update(User user) {
        System.out.print("进入数据库");
        userMapper.update(user);
    }

    @Override
    @CacheEvict(allEntries=true) //会清空所有缓存 （只会清除 get操作的缓存，不会清除 无关的缓存）
    public void delete(Long id) {
        System.out.print("进入数据库");
        userMapper.delete(id);
    }
}
