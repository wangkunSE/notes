package com.soul.alg.tencet;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/30
 * @time: 下午4:42
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Soultion {
    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            int value = l1.val > l2.val ? l2.val : l1.val;
            p.next = new ListNode(value);
            p = p.next;
            //这里需要做判断
            if (l1.val > l2.val) {
                l2 = l2.next;
            } else {
                l1 = l1.next;
            }
        }

        if (l1 != null) {
            p.next = l1;
        } else if (l2 != null) {
            p.next = l2;
        }

        return head.next;
    }
}
