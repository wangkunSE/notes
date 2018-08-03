package com.soul.alg.tencet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/30
 * @time: 下午5:01
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class RandomEmp {

    private static final Integer employeeNumber = 300000;
    private static final Integer chosenNumber = 100000;

    public int getEmployeeNumber() {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            result += rand();
        }

        return result;
    }

    //随机返回0~65535的函数
    private int rand() {

        return 0;
    }

    public List<Integer> getChosenEmployee() {
        Set<Integer> result = new HashSet<>();

        while (result.size() < chosenNumber) {
            result.add(getEmployeeNumber() % employeeNumber + 1);
        }
        ArrayList<Integer> chosenEmployeeIds = new ArrayList<>();
        chosenEmployeeIds.addAll(result);
        return chosenEmployeeIds;
    }

    public static void main(String[] args) {
        Map map = new TreeMap();
        Map map2 = new ConcurrentHashMap();
        map2.put("asd","asd");
        map2.put("asd","esf");
        map2.put("dsa","esf");
//        map2.put(null,"weqw");
        map2.size();
    }
}
