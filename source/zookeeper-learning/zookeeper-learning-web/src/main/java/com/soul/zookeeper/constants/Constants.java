package com.soul.zookeeper.constants;

/***
 * @author wangkun1
 * @version 2017/12/26 
 */
public class Constants {

    public static final String ZOOKEEPER_CLUSTER_SERVER_PATH = "192.168.241.152:2181,192.168.241.153:2181,192.168.241.154:2181";
    //    public static final String ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH = "192.168.154.151:2181,192.168.154.151:2182,192.168.154.151:2183";
    public static final String ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH = "192.168.241.151:2181,192.168.241.151:2182,192.168.241.151:2183";
    public static final String ZOOKEEPER_DOCKER_CLUSTER_SERVER_PATH = "10.5.232.78:2181,10.5.232.78:2183";
    public static final String ROOT_PATH = "/zk-test-2";
    public static final String MY_LOCK = "lock";
}
