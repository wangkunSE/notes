package com.soul.alg.datastructure;


/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/6
 * @time: 上午9:36
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode generateListNode(int[] arr1) {
        if (arr1 == null || arr1.length < 1) {
            return null;
        }
        ListNode head = new ListNode(arr1[0]);
        ListNode p = head;
        for (int i = 1; i < arr1.length; i++) {
            p.next = new ListNode(arr1[i]);
            p = p.next;
        }
        return head;
    }
}
