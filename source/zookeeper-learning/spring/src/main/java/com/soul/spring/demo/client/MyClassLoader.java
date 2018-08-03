package com.soul.spring.demo.client;

import com.soul.spring.demo.domain.Car;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/31
 * @time: 下午7:40
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MyClassLoader {


    public static void main(String[] args) throws Exception {

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] buf = new byte[is.available()];
                    is.read(buf);
                    return defineClass(name, buf, 0, buf.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myClassLoader.loadClass("com.soul.spring.demo.domain.Car").newInstance();
        while (myClassLoader != null) {
            System.out.println(myClassLoader.getClass());
            myClassLoader = myClassLoader.getParent();
        }
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj instanceof Car);
    }
}
