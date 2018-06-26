package com.soul.alg.leetcode;

import com.soul.alg.datastructure.ListNode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/6
 * @time: 上午9:35
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        First first = new First();
        int[] arr = {1, 2};
        ListNode head = ListNode.generateListNode(arr);
        ListNode listNode = first.reverseKGroup(head, 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    private static class First {

        public ListNode reverseKGroup(ListNode head, int k) {
            if (k < 1) {
                return head;
            }
            ListNode headNode = new ListNode(0);
            headNode.next = head;
            ListNode h = headNode, t = headNode;
            while (h != null && t != null) {
                int count = k;
                while (count > 0 && t != null) {
                    t = t.next;
                    count--;
                }
                if (count > 0 || t == null) {
                    break;
                }
                t = t.next;
                h.next = reverseListNode(h.next, t);
                count = k;
                while (count > 0) {
                    h = h.next;
                    count--;
                }
                t = h;
            }
            return headNode.next;
        }

        private ListNode reverseListNode(ListNode head, ListNode tail) {
            ListNode headNode = new ListNode(0);
            headNode.next = tail;
            ListNode p = head;
            ListNode r = p.next;
            while (p != tail ) {
                p.next = headNode.next;
                headNode.next = p;
                p = r;
                if (r != null) {
                    r = r.next;
                }
            }
            return headNode.next;
        }
    }
}
