package com.soul.thread;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author WK
 * @version 2018/2/25
 */
public class j_DaemonThread implements Runnable {

    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            if (i % 2 == 0) {
                threads[i].setDaemon(false);
            }
            threads[i].start();
            System.out.println("DaemonSpawn " + i + " started.");
        }

        for (int i = 0; i < threads.length; i++) {
            System.out.println("threads[" + i + "].isDaemon() == " + threads[i].isDaemon() + ".");
        }
        /*while (true){
            Thread.yield();
        }*/
    }

    private static class DaemonSpawn implements Runnable {

        @Override
        public void run() {
            /*while (true) {
                Thread.yield();
            }*/
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new j_DaemonThread());
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
