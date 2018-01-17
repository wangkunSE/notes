package com.soul.reflect.demo;

import com.soul.domain.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/***
 * @author wangkun1
 * @version 2018/1/17 
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        createInstance();
    }

    private static void createInstance() throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("Loader : " + classLoader);
        System.out.println("Parent Loader : " + classLoader.getParent());
        System.out.println("GrantParent Loader : " + classLoader.getParent().getParent());

        Class<?> loadClass = classLoader.loadClass("com.soul.domain.User");
        Constructor<?> constructor = loadClass.getDeclaredConstructor((Class<?>[]) null);
        User user = (User) constructor.newInstance();

        Method userName = loadClass.getMethod("setUserName", String.class);
        userName.invoke(user, "狗蛋");
        Method id = loadClass.getMethod("setId", String.class);
        id.invoke(user, "1");

        System.out.println(user);

    }
}
