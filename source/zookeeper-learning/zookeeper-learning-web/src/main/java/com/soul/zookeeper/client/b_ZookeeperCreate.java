package com.soul.zookeeper.client;

import com.soul.zookeeper.constants.Constants;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2017/12/26 
 */
public class b_ZookeeperCreate implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
//        syncCreateAPI();
        asyncCreateAPI();
    }

    private static void asyncCreateAPI() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = getZooKeeper();
        zooKeeper.create("/zk-asyn-test","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,new IStringCallback(),"I am context.");
        zooKeeper.create("/zk-asyn-test","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,new IStringCallback(),"I am context.");
        zooKeeper.create("/zk-asyn-test","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallback(),"I am context.");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void syncCreateAPI() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = getZooKeeper();
        String path1 = zooKeeper.create("/zk-test", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create node: " + path1);
        String path2 = zooKeeper.create("/zk-test", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create node: " + path2);
    }

    private static ZooKeeper getZooKeeper() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(Constants.ZOOKEEPER_DOCKER_CLUSTER_SERVER_PATH, 5000, new b_ZookeeperCreate());
        countDownLatch.await();
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result: [ " + rc + " , " + path + " , " + ctx + " , " + " real path name: " + name);
    }
}