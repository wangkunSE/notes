package com.soul.spring.demo.concurrent;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/1
 * @time: 上午10:13
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ValueTransfer {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public int a;
    public volatile int b;

    public static void main(String[] args) throws InterruptedException {
        ValueTransfer demo = new ValueTransfer();
        new Thread(new Task(demo)).start();
//        new Thread(new Task(demo)).start();

//        Thread.sleep(1);
        System.out.println(Thread.currentThread().getName() + " a:" + demo.a + " currentTime:" + System.currentTimeMillis());
        demo.a = 4;
        System.out.println(Thread.currentThread().getName() + " a:" + demo.a + " currentTime:" + System.currentTimeMillis());

        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " b:" + demo.b + " currentTime:" + System.currentTimeMillis());
        demo.b = 3;
        System.out.println(Thread.currentThread().getName() + " b:" + demo.b + " currentTime:" + System.currentTimeMillis());

        Thread.sleep(5000);
        System.out.println("Final a:" + demo.a);
        System.out.println("Final b:" + demo.b);
    }

    private static class Task implements Runnable {

        private ValueTransfer valueTransfer;

        public Task(ValueTransfer valueTransfer) {
            this.valueTransfer = valueTransfer;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " a:" + valueTransfer.a + " currentTime:" + System.currentTimeMillis());
            valueTransfer.a = 1;
            System.out.println(Thread.currentThread().getName() + " a:" + valueTransfer.a + " currentTime:" + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " b:" + valueTransfer.b + " currentTime:" + System.currentTimeMillis());
            valueTransfer.b = 2;
            System.out.println(Thread.currentThread().getName() + " b:" + valueTransfer.b + " currentTime:" + System.currentTimeMillis());
        }
    }
}
