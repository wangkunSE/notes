package com.soul.service;

import com.soul.dao.UserDao;
import com.soul.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

    public User getUser(String userName) {
        printClassLoader(Thread.currentThread().getContextClassLoader(), 1);
        return userDao.findUserByUserName(userName);
    }

    private void printClassLoader(ClassLoader currentClassLoader, int n) {
        if (currentClassLoader != null) {
            printClassLoader(currentClassLoader.getParent(), n + 1);
            for (int i = 0; i <= n; i++) {
                System.out.print("--");
            }
            System.out.println("CurrentClassLoader : " + currentClassLoader);
        }
    }

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:*.*");

        assert resources != null;
        for (Resource resource : resources) {
            System.out.println(resource.getDescription());
        }
    }
}
