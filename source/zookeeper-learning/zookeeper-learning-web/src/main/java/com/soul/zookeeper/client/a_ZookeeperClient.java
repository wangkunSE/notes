package com.soul.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static com.soul.zookeeper.constants.Constants.ZOOKEEPER_DOCKER_CLUSTER_SERVER_PATH;
import static com.soul.zookeeper.constants.Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH;

/***
 * @author wangkun1
 * @version 2017/12/25 
 */
public class a_ZookeeperClient implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_DOCKER_CLUSTER_SERVER_PATH, 50000, new a_ZookeeperClient());
            System.out.println(zooKeeper.getState());
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("connection has been established!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("Received event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();
        }
    }
}
