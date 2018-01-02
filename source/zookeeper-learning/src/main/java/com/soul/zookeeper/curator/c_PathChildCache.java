package com.soul.zookeeper.curator;

import com.soul.zookeeper.constants.Constants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/***
 * @author wangkun1
 * @version 2018/1/2 
 */
public class c_PathChildCache {

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectionTimeoutMs(5000)
            .connectString(Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        PathChildrenCache childrenCache = new PathChildrenCache(client,Constants.ROOT_PATH,true);
        childrenCache.start();
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {

                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED,"+pathChildrenCacheEvent.getData());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED,"+pathChildrenCacheEvent.getData());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED,"+pathChildrenCacheEvent.getData());
                        break;
                    default:
                        break;
                }
            }
        });

        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(Constants.ROOT_PATH,"".getBytes());
        Thread.sleep(1000);
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(Constants.ROOT_PATH+"/c1","".getBytes());
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded()
                .forPath(Constants.ROOT_PATH+"/c1");
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded()
                .forPath(Constants.ROOT_PATH);
        Thread.sleep(Integer.MAX_VALUE);

    }

}
