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
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2017/12/26 
 */
public class d_ZookeeperData implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();
    private static String path = "/zk-test";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//        synGetData();
        asynGetData();
    }

    private static void asynGetData() throws IOException, InterruptedException, KeeperException {
        initZookeeper();
        zooKeeper.getData(path, true, new IDataCallback(), null);
        zooKeeper.setData(path, "456".getBytes(), -1);
        zooKeeper.setData(path, "123".getBytes(), 0);
        Thread.sleep(Integer.MAX_VALUE);

    }


    private static void synGetData() throws IOException, InterruptedException, KeeperException {

        System.out.println(new String(zooKeeper.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + " , " + stat.getMzxid() + " , " + stat.getVersion());
        zooKeeper.setData(path, "456".getBytes(), -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void initZookeeper() throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH, 5000, new d_ZookeeperData());
        countDownLatch.await();
        zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }


    @Override
    public void process(WatchedEvent event) {

        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeDataChanged == event.getType()) {
                try {
//                    System.out.println(new String(zooKeeper.getData(event.getPath(), true, stat)));
//                    System.out.println(stat.getCzxid() + " , " + stat.getMzxid() + " , " + stat.getVersion());
                    zooKeeper.getData(event.getPath(), true,new IDataCallback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class IDataCallback implements AsyncCallback.DataCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println(rc + " , " + path + " , " + ctx + " , " + new String(data));
        System.out.println(stat.getCzxid() + " , " + stat.getMzxid() + " , " + stat.getVersion());
    }
}
