package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given the head of a linked list, return the list after sorting
 * it in ascending order.
 * <p>
 * Follow up: Can you sort the linked list
 * in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class SortLinkedList extends AbstractAlg {

    static class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode midNode = mid(head);
            ListNode nHead = midNode.next;
            midNode.next = null;

            return mergerLinkedList(sortList(head), sortList(nHead));
        }

        public ListNode mergerLinkedList(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) return null;
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            ListNode curr1 = l1;
            ListNode curr2 = l2;
            ListNode head = new ListNode(-1);
            ListNode res = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    res.next = l1;
                    res = l1;
                    l1 = l1.next;
                } else {
                    res.next = l2;
                    res = l2;
                    l2 = l2.next;
                }
            }
            if (l1 != null) res.next = l1;
            if (l2 != null) res.next = l2;

            return head.next;
        }

        public ListNode mid(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode slow = head;
            ListNode fast = head;

            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    static class SolutionII {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode left = new ListNode(-1);
            ListNode medium = head;
            ListNode right = new ListNode(-1);

            ListNode cur = head.next;

            ListNode l = left;
            ListNode m = medium;
            ListNode r = right;

            while (cur != null) {
                if (cur.val < m.val) {
                    l.next = cur;
                    l = l.next;
                } else if (cur.val == medium.val) {
                    m.next = cur;
                    m = m.next;
                } else {
                    r.next = cur;
                    r = r.next;
                }
                cur = cur.next;
            }

            l.next = null;
            r.next = null;
            m.next = null;

            ListNode leftNodes = sortList(left.next);
            ListNode rightNodes = sortList(right.next);

            if (leftNodes != null) {
                l = leftNodes;
                while (l.next != null) {
                    l = l.next;
                }
                l.next = medium;
                m.next = rightNodes;
                return leftNodes;
            } else {
                m.next = rightNodes;
                return medium;
            }
        }
    }
}
