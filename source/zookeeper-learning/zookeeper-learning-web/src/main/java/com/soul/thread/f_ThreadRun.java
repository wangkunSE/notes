package com.soul.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangkun1
 * @version 2018/2/1
 */
public class f_ThreadRun {


    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("run 执行了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        unModifyMapDemo();
    }

    private static void unModifyMapDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("test2", "2");
        map.put("test1", "1");
        map.put("test3", "3");
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(map);
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(concurrentHashMap);
        printMapValue(concurrentHashMap);
        System.out.println("--------");
        printMapValue(unmodifiableMap);

        System.out.println("======================");
        map.put("test4","4");
        printMapValue(concurrentHashMap);
        System.out.println("--------");
        printMapValue(unmodifiableMap);


        System.out.println("======================");
        concurrentHashMap.put("test4","4");
        printMapValue(concurrentHashMap);
        System.out.println("--------");
        printMapValue(unmodifiableMap);

        unmodifiableMap.put("test5","5");


    }

    private static void printMapValue(Map<String, String> concurrentHashMap) {
        Set<Map.Entry<String, String>> entries = concurrentHashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
