package com.soul.web;

import com.soul.domain.ErrorMessage;
import com.soul.domain.User;
import com.soul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
