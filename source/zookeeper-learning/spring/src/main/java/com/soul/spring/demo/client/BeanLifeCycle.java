package com.soul.spring.demo.client;

import com.soul.spring.demo.domain.Car;
import com.soul.spring.demo.processor.MyBeanPostProcessor;
import com.soul.spring.demo.processor.MyInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author wangkun1
 * @version 2018/1/26
 */
public class BeanLifeCycle {

    public static void main(String[] args) {
//        beanLifeCycle();
        applicationContextBeanLifeCycle();
    }

    private static void applicationContextBeanLifeCycle() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
        car.setColor("黑色");

        Car car1 = (Car) applicationContext.getBean("car");
        System.out.println(car == car1);


    }

    private static void beanLifeCycle() {

        Resource resource = new ClassPathResource("spring/applicationContext.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
        reader.loadBeanDefinitions(resource);

        ((ConfigurableBeanFactory)beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory)beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        car.setColor("黑色");

        Car car1 = (Car) beanFactory.getBean("car");
        System.out.println(car == car1);

        ((DefaultListableBeanFactory) beanFactory).destroySingletons();
    }
}
