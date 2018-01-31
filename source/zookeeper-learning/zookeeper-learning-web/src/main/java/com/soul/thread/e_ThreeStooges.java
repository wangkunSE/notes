package com.soul.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangkun1
 * @version 2018/1/31
 */
public class e_ThreeStooges {
    private static final Set<String> stooges = new HashSet<>();

    public e_ThreeStooges(){
        stooges.add("Jack");
        stooges.add("Tom");
        stooges.add("Jerry");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }

}
