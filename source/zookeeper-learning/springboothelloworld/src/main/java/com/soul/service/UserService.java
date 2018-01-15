package com.soul.service;

import com.soul.dao.UserDao;
import com.soul.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * @author wangkun1
 * @version 2018/1/12 
 */
@Service
@Transactional
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String userName){

        return userDao.findUserByUserName(userName);
    }
}
