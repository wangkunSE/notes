package com.soul.alg.lock;

import java.util.LinkedHashMap;

/**
 * @author wangkunwk
 * @version 2020/8/20
 */
public class DeadLock {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();


    public void deadLockMethodDemo() throws InterruptedException {

        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "get lockA");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " tryAcquire lockB");
            synchronized (resourceB){
                System.out.println(Thread.currentThread().getName() + "get lockB");
            }
        }
    }

    public void deadLockMethodDemoII() throws InterruptedException {
        synchronized (resourceB) {
            System.out.println(Thread.currentThread().getName() + "get lockB");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " tryAcquire lockA");
            synchronized (resourceA){
                System.out.println(Thread.currentThread().getName() + "get lockA");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock = new DeadLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.deadLockMethodDemo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        deadLock.deadLockMethodDemoII();
    }


}
