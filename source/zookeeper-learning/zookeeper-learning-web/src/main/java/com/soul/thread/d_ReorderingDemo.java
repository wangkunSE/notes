package com.soul.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangkun1
 * @version 2018/1/31
 */
public class d_ReorderingDemo {

    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {

        private  AtomicInteger threadId = new AtomicInteger(0);
        private  ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(threadId::getAndIncrement);

        public long getId() {
            return threadLocal.get();
        }

        @Override
        public void run() {
//            while (!ready) {
//                Thread.yield();
//            }
//            System.out.println(number);
            System.out.println("当前线程Id:" + threadLocal.get());
            System.out.println("当前AtomicInteger的值：" + threadId.get());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new ReaderThread().start();
        }
//        number = 12;
//        ready = true;
    }
}
