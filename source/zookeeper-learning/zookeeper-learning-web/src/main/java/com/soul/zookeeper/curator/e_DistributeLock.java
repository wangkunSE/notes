package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static com.soul.zookeeper.constants.Constants.MY_LOCK;

/***
 * @author wangkun1
 * @version 2018/1/2 
 */
public class e_DistributeLock {


    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectionTimeoutMs(5000)
            .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    static String LOCK_PATH = Constants.ROOT_PATH + "/lock";

    public static void main(String[] args) throws Exception {
//        new e_DistributeLock().lockDemo();
        synchronizedDemo();
//        bankDemo();
    }

    private static void bankDemo() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bank bank = new Bank();
                    bank.deposit(bank, 100);
                }
            }).start();
        }
    }

    private void lockDemo() {

        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    lock.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                createOrder();
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.countDown();
    }

    private void createOrder() {
        synchronized (MY_LOCK) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
            String format = sdf.format(new Date());
            System.out.println("生成的订单号是：" + format);
        }
    }

    private static void synchronizedDemo() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    private static class Task implements Runnable {
        private static final String MY_LOCK_DATE = "date_lock";
        private Integer value = 0;
        private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");

        @Override
        public void run() {
            createOrder();
        }

        private void createOrder() {
            synchronized (MY_LOCK_DATE) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ConcurrentHashMap
                String format = sdf.format(new Date());
                System.out.println(Thread.currentThread().getName() + "生成的订单号是：" + format);
                value++;
                System.out.println(Thread.currentThread().getName() + "当前的value值为：" + value);
            }
        }
    }

    static class Bank {

        private static int money = 1000;

        public void deposit(Bank bank, int money) {
            synchronized (MY_LOCK) {
                // synchronized (Bank.class) { // 全局锁
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "--当前银行余额为：" + Bank.money);
                Bank.money += money;
                System.out.println(threadName + "--存入后银行余额为：" + Bank.money);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // }
            }
        }
    }
}
