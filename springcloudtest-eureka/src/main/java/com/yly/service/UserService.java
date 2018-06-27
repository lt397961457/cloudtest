package com.yly.service;

import com.yly.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public interface UserService {
    List<User> getAll();


    User getOne(Long id);

    void insert(User user);


    void update(User user);


    void delete(Long id);
}
