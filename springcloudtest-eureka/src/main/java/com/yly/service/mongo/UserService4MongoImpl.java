package com.yly.service.mongo;

import com.yly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/1.
 */
@Service
public class UserService4MongoImpl implements UserService4Mongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User getOne(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        User user = mongoTemplate.findOne(query,User.class);
        return user;
    }

    @Override
    public void insert(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
