package com.soul.zookeeper.zkclient;

import com.soul.zookeeper.constants.Constants;
import org.I0Itec.zkclient.ZkClient;

/***
 * @author wangkun1
 * @version 2017/12/27 
 */
public class a_ZkClientCreate {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH,5000);
        zkClient.createPersistent("/zk-test/c1",true);
        Thread.sleep(5000);
        zkClient.deleteRecursive("/zk-test");
    }
}
