package com.soul.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;
import org.apache.zookeeper.server.quorum.Vote;

import java.io.File;

/***
 * @author wangkun1
 * @version 2018/1/3 
 */
public class h_TestingServer {

    static String path = "/zookeeper";

    public static void main(String[] args) throws Exception {
//        testServer();
//        System.out.println(new Properties().getProperty("java.io.tmpdir"));
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        Thread.sleep(2000);

        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zooKeeperServer : cluster.getServers()) {
            System.out.print(zooKeeperServer.getInstanceSpec().getServerId() + "-");
            System.out.print(zooKeeperServer.getQuorumPeer().getServerState() + "-");
            System.out.println(zooKeeperServer.getInstanceSpec().getDataDirectory().getAbsolutePath());
            if (zooKeeperServer.getQuorumPeer().getServerState().equals("leading")) {
                leader = zooKeeperServer;
            }

        }
        assert leader != null;
        leader.kill();
        System.out.println("--After leader kill:");
        for (TestingZooKeeperServer zooKeeperServer : cluster.getServers()) {
            System.out.print(zooKeeperServer.getInstanceSpec().getServerId() + "-");
            System.out.print(zooKeeperServer.getQuorumPeer().getServerState() + "-");
            System.out.println(zooKeeperServer.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        Thread.sleep(5000);
        for (TestingZooKeeperServer zooKeeperServer : cluster.getServers()) {
            System.out.print(zooKeeperServer.getInstanceSpec().getServerId() + "-");
            System.out.print(zooKeeperServer.getQuorumPeer().getServerState() + "-");
            System.out.println(zooKeeperServer.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }
        cluster.stop();
    }


    private static void testServer() throws Exception {
        TestingServer server = new TestingServer(2181, new File("E://home/admin"));

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        System.out.println(client.getChildren().forPath(path));
        server.close();
    }
}
