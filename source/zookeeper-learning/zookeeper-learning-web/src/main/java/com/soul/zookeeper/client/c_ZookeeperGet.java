package com.soul.zookeeper.client;

import com.soul.zookeeper.constants.Constants;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2017/12/26 
 */
public class c_ZookeeperGet implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String path;
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//        synGetChildrenAPI();
        asynGetChildrenAPI();
    }

    private static void asynGetChildrenAPI() throws IOException, InterruptedException, KeeperException {
        zooKeeper = getZooKeeper();
        path = "/zk-test";
        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getChildren(path, true, new IChildren2Callback(), null);
        zooKeeper.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void synGetChildrenAPI() throws IOException, InterruptedException, KeeperException {
        zooKeeper = getZooKeeper();
        path = "/zk-test";
        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        List<String> children = zooKeeper.getChildren(path, true);
        System.out.println(children);
        zooKeeper.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static ZooKeeper getZooKeeper() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH, 5000, new c_ZookeeperGet());
        countDownLatch.await();
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeChildrenChanged == event.getType()) {
                try {
                    System.out.println("ReGet Children:" + zooKeeper.getChildren(event.getPath(), true));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class IChildren2Callback implements AsyncCallback.Children2Callback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("Get Children znode result: [ response code : " + rc + " ,path: " +
                path + " ,context: " + ctx + " ,current children list: " + children + " ,stat: " + stat + " ]");
    }
}
