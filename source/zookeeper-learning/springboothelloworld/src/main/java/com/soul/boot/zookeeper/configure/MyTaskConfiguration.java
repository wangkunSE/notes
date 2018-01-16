package com.soul.boot.zookeeper.configure;

import com.soul.boot.zookeeper.task.MyTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/***
 * @author wangkun1
 * @version 2018/1/15 
 */
@Configuration
@Order(3)
public class MyTaskConfiguration {

    @Bean(initMethod = "startTask")
    public MyTask task(){
        return new MyTask();
    }
}
