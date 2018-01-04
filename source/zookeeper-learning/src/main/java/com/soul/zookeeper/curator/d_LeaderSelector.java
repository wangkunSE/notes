package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2018/1/2 
 */
public class d_LeaderSelector {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = CuratorFrameworkFactory.builder()
                            .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
                            .connectionTimeoutMs(5000)
                            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                            .build();
                    client.start();
                    LeaderSelector selector = new LeaderSelector(client, Constants.ROOT_PATH, new LeaderSelectorListenerAdapter() {
                        @Override
                        public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                            System.out.println(Thread.currentThread().getName() + " Become new Master!");
                            Thread.sleep(3000);
                            System.out.println(Thread.currentThread().getName() + " Finish Master Operation , release lock");
                        }
                    });
                    selector.autoRequeue();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    selector.start();
                }
            }).start();
        }
        countDownLatch.countDown();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
