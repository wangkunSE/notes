package com.soul.boot.zookeeper.task;

import com.soul.boot.zookeeper.client.ZKUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * @author wangkun1
 * @version 2018/1/15 
 */
public class MyTask {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final String CHILDREN_PATH = "/zk-test/child-event";
    private ZKUtils zkUtils;

    @Autowired
    public void setZkUtils(ZKUtils zkUtils) {
        this.zkUtils = zkUtils;
    }

    public void startTask() {

        CuratorFramework client = zkUtils.getClient();
        try {
            if (null == client) {
                System.out.println("[> client is null....< ]");
                return;
            }
            Stat stat = client.checkExists().forPath(CHILDREN_PATH);
            if (stat != null) {
                client.delete().guaranteed()
                        .deletingChildrenIfNeeded()
                        .forPath(CHILDREN_PATH);
            }
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(CHILDREN_PATH, "init".getBytes());
            PathChildrenCache childrenCache = new PathChildrenCache(client, CHILDREN_PATH, true);
            childrenCache.start();
            childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {

                    switch (pathChildrenCacheEvent.getType()) {
                        case CHILD_ADDED:
                            logger.info("PATH:/zk-test/child-event: " + "CHILD_ADDED," + pathChildrenCacheEvent.getData());
                            break;
                        case CHILD_UPDATED:
                            logger.info("PATH:/zk-test/child-event: " + "CHILD_UPDATED," + pathChildrenCacheEvent.getData());
                            break;
                        case CHILD_REMOVED:
                            logger.info("PATH:/zk-test/child-event: " + "CHILD_REMOVED," + pathChildrenCacheEvent.getData());
                            break;
                        default:
                            break;
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("[> ====== SERVICE STARTED !! ========== <]");
    }
}
