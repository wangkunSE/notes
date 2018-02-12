package com.soul.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wangkun1
 * @version 2018/2/7
 */
public class h_Semaphore {
    /**
     * 通过Semaphore为set做边界
     *
     * @param <T>
     */
    public static class BoundedHashSet<T> {
        private final Set<T> set;
        private final Semaphore sem;

        public BoundedHashSet(int bound) {
            this.set = Collections.synchronizedSet(new HashSet<T>());
            this.sem = new Semaphore(bound);
        }

        public boolean add(T o) throws InterruptedException {
            sem.acquire();
            boolean wasAdd = false;
            try {
                wasAdd = set.add(o);
                return wasAdd;
            } finally {
                if (!wasAdd) {
                    sem.release();
                }
            }
        }

        public boolean remove(T o) {
            boolean wasRemoved = set.remove(o);
            if (wasRemoved) {
                sem.release();
            }
            return wasRemoved;
        }

        @Override
        public String toString() {
            return set.toString();
        }
    }

    public static void main(String[] args) {
//        try {
//            testBoundedHashSet();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Executors.newCachedThreadPool();
        testListRemove();
    }

    private static void testListRemove() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        System.out.println(list.size());
        list.remove(0);
        list.remove(0);
        list.remove(0);
        System.out.println(list.size());
    }

    private static void testBoundedHashSet() throws InterruptedException {
        BoundedHashSet<String> set = new BoundedHashSet<>(3);
        set.add("str");
        set.add("小红");
        set.add("小丽");
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(set);
            set.remove("str");
        }).start();
        set.add("小美");
        System.out.println(set);
        Thread.sleep(4000);
        set.remove("str");
        System.out.println(set);
    }
}
