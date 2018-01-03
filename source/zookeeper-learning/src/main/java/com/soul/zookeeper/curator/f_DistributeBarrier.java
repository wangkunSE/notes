package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/***
 * @author wangkun1
 * @version 2018/1/2 
 */
public class f_DistributeBarrier {

    private static String BARRIER_PATH = Constants.ROOT_PATH + "/barrier";

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder()
                                .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
                                .connectionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                .build();
                        client.start();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, BARRIER_PATH, 8);
                        Thread.sleep(Math.round(Math.random() * 3000));
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");
                        barrier.enter();
                        System.out.println(Thread.currentThread().getName() + "启动...");
                        Thread.sleep(Math.round(Math.random() * 3000));
                        barrier.leave();
                        System.err.println(Thread.currentThread().getName() + "退出...");
                    } catch (Exception ignored) {
                    }
                }
            }).start();
        }
    }
}
