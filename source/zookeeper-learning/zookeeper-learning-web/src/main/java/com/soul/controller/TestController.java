package com.soul.controller;

import com.alibaba.fastjson.JSON;
import com.soul.webuser.WebUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WK
 * @version 2018/2/22
 */
@Controller
@RequestMapping("/thread/test")
public class TestController {

    @RequestMapping("/webUser")
    @ResponseBody
    public String getUser(){
        if (WebUser.getUser() != null){
            return JSON.toJSONString(WebUser.getUser());
        }
        return "There has no User !";
    }
}
