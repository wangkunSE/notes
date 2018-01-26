package com.soul.web;

import com.google.common.collect.Lists;
import com.soul.domain.ErrorMessage;
import com.soul.domain.User;
import com.soul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/***
 * @author wangkun1
 * @version 2018/1/12 
 */
@RestController
public class UserController implements ErrorController {

    private final String PATH = "/error";
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/index.html"})
    @ResponseBody
    public User loginPage(String userName) {
        return userService.getUser(userName);
    }

    @RequestMapping(value = PATH)
    @ResponseBody
    public ErrorMessage error() {
        return new ErrorMessage(ErrorMessage.FailEnum.FAIL_ENUM);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    public static void main(String[] args) {
//        System.out.println(org.apache.commons.lang.StringUtils.isNumeric(""));
        List<String> list = Lists.newArrayList();
        list.add(null);
        list.add("Jack");
        list.add(null);
        System.out.println(list);
        list.removeAll(Collections.singleton(""));
        System.out.println(list);
    }
}
