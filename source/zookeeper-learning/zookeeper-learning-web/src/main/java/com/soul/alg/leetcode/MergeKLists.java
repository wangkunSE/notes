package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/5
 * @time: 上午10:20
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MergeKLists {

    public static void main(String[] args) {
        First first = new First();
        ListNode[] lists = new ListNode[3];
        int[] arr1 = {};
        ListNode l1 = generateListNode(arr1);
        int[] arr2 = {};
        ListNode l2 = generateListNode(arr2);
        int[] arr3 = {2, 6};
        ListNode l3 = generateListNode(arr3);
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        ListNode listNode = first.mergeKLists(lists);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode generateListNode(int[] arr1) {
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

    private static class First {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length <= 0) {
                return null;
            }
            int k = 1, count = 1;
            while (true) {
                for (int i = 0; i < lists.length - k; i += k + 1) {
                    lists[i] = mergeTwoLists(lists[i], lists[i + k]);
                    lists[i + k] = null;
                }
                if (k > (lists.length / 2)) {
                    break;
                }
                k = (int) Math.pow(2, count++);
            }
            return lists[0];
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            ListNode res = null, low = null, high = null;
            if (l1.val > l2.val) {
                res = new ListNode(l2.val);
                low = l2.next;
                high = l1;
            } else {
                res = new ListNode(l1.val);
                low = l1.next;
                high = l2;
            }
            ListNode p = res;
            while (low != null && high != null) {
                if (low.val > high.val) {
                    p.next = new ListNode(high.val);
                    high = high.next;
                } else {
                    p.next = new ListNode(low.val);
                    low = low.next;
                }
                p = p.next;
            }
            ListNode rest = low == null ? high : low;
            while (rest != null) {
                p.next = new ListNode(rest.val);
                p = p.next;
                rest = rest.next;
            }
            return res;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
