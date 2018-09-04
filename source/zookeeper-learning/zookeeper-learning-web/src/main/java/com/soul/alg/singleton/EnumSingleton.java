package com.soul.alg.singleton;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/25
 * @time: 下午1:57
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class EnumSingleton {

    private EnumSingleton() {
    }

    public EnumSingleton getInstance() {
        return Singleton.INSTANCE.getEnumSingleton();
    }

    private enum Singleton {
        INSTANCE;

        private EnumSingleton enumSingleton;

        private Singleton() {
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton getEnumSingleton() {
            return enumSingleton;
        }
    }
}
