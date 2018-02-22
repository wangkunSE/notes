package com.soul.webuser;

import com.soul.domain.User;

/**
 * @author WK
 * @version 2018/2/22
 */
public class WebUser {

    private static ThreadLocal<User> userContainer = new ThreadLocal<User>();

    public static User getUser(){
        return userContainer.get();
    }

    public static boolean setUser(User user){
        if (userContainer.get()!= null){
            return false;
        }
        userContainer.set(user);
        return true;
    }
}
