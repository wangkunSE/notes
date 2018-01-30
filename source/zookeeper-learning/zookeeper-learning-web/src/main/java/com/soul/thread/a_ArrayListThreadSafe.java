package com.soul.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2018/1/3 
 */
public class a_ArrayListThreadSafe {

    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static CountDownLatch count = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        final List list = new ArrayList<String>();
        listAdd(list,8);
        listGet(list,3);
        countDownLatch.countDown();
        count.await();
        System.out.println(list);
    }

    private static void listGet(final List list, int threadNum) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"读取到了："+list.remove(0));
                count.countDown();
            }).start();
        }
    }

    private static void listAdd(final List list,int threadNum) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("lll"+ Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+"添加了内容...");
                count.countDown();
            }).start();
        }
    }
}
