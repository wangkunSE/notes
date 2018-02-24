package com.soul.thread;

/**
 * @author WK
 * @version 2018/2/24
 */
public class i_ShutdownHook {

    private static class HookTask implements Runnable {

        @Override
        public void run() {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("Hook finished!!!");
                }
            });
            System.out.println("========================");
            System.out.println(Thread.currentThread().getName() + "    I am running!!");
            System.out.println("========================");
        }
    }

    public static void main(String[] args) {
        new Thread(new HookTask()).start();
    }
}
