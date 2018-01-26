package com.soul.spring.demo.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author wangkun1
 * @version 2018/1/26
 */
public class Car implements BeanFactoryAware , BeanNameAware , InitializingBean ,DisposableBean{

    public Car(){
        System.out.println("Car 构造方法被调用.");
    }

    private String brand;
    private String color;
    private Integer speed;
    private BeanFactory beanFactory;
    private String beanName;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("Car.setBrand方法被调用.");
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory方法.");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("调用BeanNameAware.setBeanName方法.");
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet方法.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy方法.");
    }

    public void myInit(){
        System.out.println("调用init-method指定的myInit方法.将speed设置成240");
        this.speed = 240;
    }

    public void myDestroy(){
        System.out.println("调用destroy-method指定的myDestroy方法.");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", speed=" + speed +
                '}';
    }
}
