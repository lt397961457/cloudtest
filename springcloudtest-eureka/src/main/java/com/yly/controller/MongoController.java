package com.yly.controller;

import com.yly.entity.User;
import com.yly.service.mongo.UserService4Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/1.
 */
@Controller
@RequestMapping("/mongo/user")
public class MongoController {


    @Autowired
    private UserService4Mongo userService4Mongo;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public User getUserByName(@PathVariable("id") Long id ){
        User user = new User();
        user = userService4Mongo.getOne(id);
        return user;
    }
    @PostMapping(value = "/save")
    @ResponseBody
    public void save(@RequestBody User user){
        userService4Mongo.insert(user);
    }
}
