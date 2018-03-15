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

    private static class Task implements Runnable{

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
//        Thread thread = new Thread(new Task());
//        thread.start();
//        thread.run();
//        thread.start();
        int cpuNumbers = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNumbers);
    }

}
