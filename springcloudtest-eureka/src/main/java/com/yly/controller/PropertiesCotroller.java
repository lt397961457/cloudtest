package com.yly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/11/30.
 */
@Controller
@RequestMapping("/peoperties")
public class PropertiesCotroller {
    @Value("${com.yly.title}")
    private String title;
    @Value("${com.yly.description}")
    private String desc;

    @RequestMapping("/getValue")
    @ResponseBody
    public String getPropertise(){
        return title + "|" +desc;
    }
}
