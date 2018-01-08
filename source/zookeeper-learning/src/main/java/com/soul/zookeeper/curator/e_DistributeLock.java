package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

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
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String format = sdf.format(new Date());
                    System.out.println("生成的订单号是："+format);
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        ReentrantLock lock1 = new ReentrantLock();
        lock1.lock();
        countDownLatch.countDown();
    }
}
