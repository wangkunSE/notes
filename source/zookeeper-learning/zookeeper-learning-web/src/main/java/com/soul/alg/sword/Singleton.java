package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/11
 */
public class Singleton {

    private Singleton(){

    }
    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

}
