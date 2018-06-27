package com.yly.service.mongo;

import com.yly.entity.User;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface UserService4Mongo {
    User getOne(Long id);

    void insert(User user);


    void update(User user);


    void delete(Long id);

}
