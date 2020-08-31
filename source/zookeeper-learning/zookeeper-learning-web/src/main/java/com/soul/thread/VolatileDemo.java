package com.soul.thread;

/**
 * @author wangkunwk
 * @version 2020/8/25
 */
public class VolatileDemo {

    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (!flag) {
                System.out.println(Thread.currentThread().getName() + " is running!");
            }

            System.out.println(Thread.currentThread().getName() + " stopped!");
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            flag = true;
            int log = 0;
            System.out.println(Thread.currentThread().getName() + "has done!");
            while (true) {
                if (log % 100000000 == 0) {
                    System.out.println(" waiting....");
                }
                log++;
            }
        }).start();


    }


}
