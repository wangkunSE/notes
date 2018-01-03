package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/***
 * @author wangkun1
 * @version 2018/1/3 
 */
public class g_ZKPaths {

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectionTimeoutMs(5000)
            .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    static String nameSpace = "/base";

    public static void main(String[] args) throws Exception {
        client.start();
//        zkPathTest();
        client.usingNamespace(nameSpace);

    }

    private static void zkPathTest() throws Exception {
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();

        System.out.println(ZKPaths.fixForNamespace(nameSpace, "/sub"));
        System.out.println(ZKPaths.makePath(nameSpace, "sub"));

        System.out.println(ZKPaths.getNodeFromPath(nameSpace + "/sub1"));
        ZKPaths.PathAndNode node = ZKPaths.getPathAndNode(nameSpace + "/sub1");
        System.out.println(node.getNode());
        System.out.println(node.getPath());

        String dir1 = nameSpace + "/child1";
        String dir2 = nameSpace + "/child2";

        ZKPaths.mkdirs(zooKeeper, dir1);
        ZKPaths.mkdirs(zooKeeper, dir2);
        System.out.println(ZKPaths.getSortedChildren(zooKeeper, nameSpace));
        ZKPaths.deleteChildren(zooKeeper, nameSpace, true);
    }
}
