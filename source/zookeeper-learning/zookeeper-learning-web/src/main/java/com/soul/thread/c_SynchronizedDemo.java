package com.soul.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangkun1
 * @version 2018/1/30
 */
public class c_SynchronizedDemo {

    public static void main(String[] args) {
        sellTickets();
    }

    private static void sellTickets() {

        Tickets tickets = new Tickets();
        for (int i = 0; i < 10; i++) {
            new Thread(tickets).start();
        }
    }
}

class Tickets implements Runnable {

    private Integer tickets = 1000;
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
//        sellTicketWithLock();
        sellTicketWithSyn();
    }

    private void sellTicketWithSyn() {
        while (tickets > 0) {
            synchronized (lock) {
                if (tickets == 90) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": 内急，去个厕所");
                        tickets--;
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (tickets == 30){
                    this.notify();
                    System.out.println("快点儿干活了，再上厕所扣工资！！");
                }
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + ": 卖出了第" + tickets-- + "张票");
                }
            }
        }
    }

    private void sellTicketWithLock() {
        while (tickets > 0) {
            lock.lock();
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖出了第" + tickets-- + "张票");
                if (tickets == 90) {
                    System.out.println(Thread.currentThread().getName() + ": 累了，正在休息");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 苏醒了，开始工作");
                }
            }
            lock.unlock();
        }
    }
}
