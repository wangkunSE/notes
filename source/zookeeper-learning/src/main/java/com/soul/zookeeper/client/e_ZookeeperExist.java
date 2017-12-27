package com.soul.zookeeper.client;

import com.soul.zookeeper.constants.Constants;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2017/12/27 
 */
public class e_ZookeeperExist implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();
    private static String path = "/zk-test";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        asynGetData();
    }

    private static void asynGetData() throws IOException, InterruptedException, KeeperException {
        initZookeeper();
        zooKeeper.exists("/",true);
        Stat stat = zooKeeper.setData(path, "123".getBytes(), -1);
        zooKeeper.setData(path, "123".getBytes(), stat.getVersion());
        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.delete(path + "/c1", -1);
        zooKeeper.delete(path, -1);

        Thread.sleep(Integer.MAX_VALUE);

    }


    private static void initZookeeper() throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH, 5000, new e_ZookeeperExist());
        countDownLatch.await();
        zooKeeper.exists(path, true);
        zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }


    @Override
    public void process(WatchedEvent event) {

        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ") Created");
                    zooKeeper.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ") Deleted");
                    zooKeeper.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ") DataChanged");
                    zooKeeper.exists(event.getPath(), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
