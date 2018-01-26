package com.soul.spring.demo.processor;

import com.soul.spring.demo.domain.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author wangkun1
 * @version 2018/1/26
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (beanName.equals("car")){
            Car car = (Car) bean;
            if (car.getColor() == null){
                System.out.println("调用BeanPostProcessor.postProcessBeforeInitialization方法.设置颜色为红色");
                car.setColor("红色");
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (beanName.equals("car")){
            Car car = (Car) bean;
            if (car.getSpeed() >= 200 ){
                System.out.println("调用BeanPostProcessor.postProcessAfterInitialization方法.设置速度为200");
                car.setSpeed(200);
            }
        }
        return bean;
    }
}
