package com.soul.alg.leetcode2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import sun.misc.Unsafe;

/**
 * @author wangkunwk
 * @version 2020/8/5
 */
public class AbstractAlg {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static ListNode buildListNode(int[] arr) {
            if (Objects.isNull(arr)) {
                return new ListNode();
            }

            ListNode listNode = new ListNode();
            ListNode p = listNode;
            for (int value : arr) {
                p.next = new ListNode(value);
                p = p.next;
            }
            return listNode.next;
        }

        public static void printLinkList(ListNode listNode) {
            if (Objects.isNull(listNode)) {
                System.out.println("NULL head!");
            }
            ListNode p = listNode;
            while (Objects.nonNull(p)) {
                System.out.println(p.val);
                p = p.next;
            }

        }
    }


    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void printArr(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {


        Class<Unsafe> aClass = (Class<Unsafe>) Class.forName("sun.misc.Unsafe");
        Constructor<Unsafe> declaredConstructor = aClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Unsafe unsafe = declaredConstructor.newInstance();

        long memory = unsafe.allocateMemory(1000L);
        System.out.println(memory);

        int[] arr = new int[20];
        arr[0] = 1;
        arr[1] = 1234;
        int i1 = unsafe.arrayIndexScale(int[].class);
        System.out.println(i1);

        Field length = int[].class.getDeclaredField("len");
        long objectFieldOffset = unsafe.objectFieldOffset(length);
        System.out.println("length offset "+objectFieldOffset);

        int i = unsafe.arrayBaseOffset(int[].class);
        System.out.println(i);

        int anInt = unsafe.getInt(arr, 4+16);
        System.out.println(anInt);

        unsafe.putInt(arr,4+32,333333);

        System.out.println(unsafe.getInt(arr,4+32));

        unsafe.putInt(arr,0,50);

        int i2 = unsafe.addressSize();
        System.out.println("address " + i2  );

        System.out.println(arr.length);




        unsafe.setMemory(memory, 100, (byte) 0);


//        readWriteLock.writeLock().lock();

        Thread.sleep(10000L);

        new Thread(() ->
                readWriteLock.readLock().lock()
                , "thread-111111111").start();
        Thread.sleep(10000L);

        new Thread(() ->
                readWriteLock.readLock().lock()
                , "thread-22222222").start();
        Thread.sleep(10000L);

        new Thread(() ->
                readWriteLock.readLock().lock()
                , "thread-333333333").start();

        Thread.sleep(10000L);

        new Thread(() ->
                readWriteLock.writeLock().lock()
                , "thread-444444").start();

        Thread.sleep(10000L);

//        readWriteLock.writeLock().unlock();

        System.out.println((1 << 16) - 1);
    }
}
