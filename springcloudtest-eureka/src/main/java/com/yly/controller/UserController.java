package com.yly.controller;

import com.yly.mapper.UserMapper;
import com.yly.entity.User;
import com.yly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/11/30.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public User getUserByName(@PathVariable("id") Long id ){
        User user = new User();
        user = userService.getOne(id);
        return user;
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public void save(@RequestBody User user){
        userService.insert(user);
    }
    @PostMapping("/update")
    @ResponseBody
    public void update(@RequestBody User user){
        userService.update(user);
    }
    @RequestMapping("/del/{id}")
    @ResponseBody
    public void delUserById(@PathVariable("id") Long id ){
        userService.delete(id);
    }
}
