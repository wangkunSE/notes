package com.soul.alg.leetcode;

/**
 * @author wangkun1
 * @version 2018/4/16
 */
public class MergeTwoSortedLists {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(Integer.MAX_VALUE);
        ListNode p = result;
        while (l1 != null && l2 != null) {
            ListNode tempNode;
            if (l1.val <= l2.val) {
                tempNode = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                tempNode = new ListNode(l2.val);
                l2 = l2.next;
            }
            p.next = tempNode;
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        result = result.next;
        return result;
    }
}
