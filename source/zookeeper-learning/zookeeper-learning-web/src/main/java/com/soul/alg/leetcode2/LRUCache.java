package com.soul.alg.leetcode2;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangkunwk
 * @version 2020/9/15
 */
public class LRUCache<K, V extends Cacheable> {

    private static final Integer DEFAULT_SIZE = 16;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Integer MAX_SIZE;

    private Map<K, V> CONTAINER;
    private LRUNode head;
    private LRUNode tail;

    public LRUCache() {
        this(DEFAULT_SIZE);
    }

    public LRUCache(int size) {
        if (size < DEFAULT_SIZE) {
            size = DEFAULT_SIZE;
        }
        MAX_SIZE = size;
        CONTAINER = new ConcurrentHashMap<>(MAX_SIZE);
        head = new LRUNode(null);
        tail = head;
    }

    public boolean put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            if (CONTAINER.containsKey(key)) {
                CONTAINER.put(key, value);
                moveToHead(key);
            } else {
                CONTAINER.put(key, value);
                LRUNode curNode = new LRUNode(key);
                curNode.next = head.next;
                head.next.pre = curNode;
                head.next = curNode;
                curNode.pre = head;
            }

            if (CONTAINER.size() > MAX_SIZE) {
                CONTAINER.remove(tail.key);
                removeTail();
            }
            return true;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void moveToHead(K key) {
        LRUNode pNode = head.next;
        while (pNode != null && pNode.key != key) {
            pNode = pNode.next;
        }

        if (Objects.nonNull(pNode)) {
            pNode.pre.next = pNode.next;
            pNode.next.pre = pNode.pre;

            head.next.pre = pNode;
            pNode.next = head.next;
            pNode.pre = head;
            head.next = pNode;
        }

    }

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {

            if (CONTAINER.containsKey(key)) {
                V v = CONTAINER.get(key);
                moveToHead(key);
                return v;

            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


    private void removeTail() {
        if (tail != null) {
            LRUNode pre = tail.pre;
            pre.next = null;
            tail = pre;
        }
    }


    class LRUNode {
        private K key;
        private LRUNode pre;
        private LRUNode next;

        public LRUNode(K key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        System.out.println( 11111+232323232+"asdasdas"+"eqweqwe");
    }


}
