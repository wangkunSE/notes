package com.soul.zookeeper.zkclient;

import com.soul.zookeeper.constants.Constants;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/***
 * @author wangkun1
 * @version 2017/12/27 
 */
public class b_ZkClientGet {

    public static void main(String[] args) throws InterruptedException {

        String path = "/zk-test";
        ZkClient zkClient = new ZkClient(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH, 5000);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + " 's child changed ,currentChildren: " + list);
            }
        });

        zkClient.createPersistent(path);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(1000);
        zkClient.createPersistent(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
