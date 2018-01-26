package com.soul.spring.demo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @author wangkun1
 * @version 2018/1/26
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    //bean实例化之前
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("car")) {
            System.out.println("***InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation" +
                    "(bean实例化之前)方法执行");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    //bean实例化之后
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("car")) {
            System.out.println("&&&InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation" +
                    "(bean实例化之后)方法执行");
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (beanName.equals("car")) {
            System.out.println("$$$InstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues" +
                    "(bean设置某个属性是调用)方法执行");
            for (PropertyDescriptor pd : pds) {
                System.out.println(pd.getName().equals("brand") ? "brand属性被操作----" : "其他属性被操作" + pd.getName());
            }
        }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
