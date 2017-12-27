package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/***
 * @author wangkun1
 * @version 2017/12/27 
 */
public class a_ZookeeperConnection {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        classicalStyle(retryPolicy);
        fluentStyle(retryPolicy);
    }

    private static void fluentStyle(RetryPolicy retryPolicy) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath("/zk-test", "".getBytes());
        List<String> list = client.getChildren().forPath("/");
        System.out.println(list);
        Thread.sleep(Integer.MAX_VALUE);

    }

    private static void classicalStyle(RetryPolicy retryPolicy) throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.newClient(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH,
                5000, 3000, retryPolicy);
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
