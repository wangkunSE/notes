package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * @author wangkunwk
 * @version 2020/9/22
 */
public class ReverseLinkList extends AbstractAlg {


    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode p = head;
            ListNode newHead = new ListNode();
            while (Objects.nonNull(p)) {
                ListNode r = p.next;
                p.next = newHead.next;
                newHead.next = p;
                p = r;
            }

            return newHead.next;
        }
    }

    static class SolutionII {
        public ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode tail = reverse(head.next);
            head.next.next = head;
            head.next = null;
            return tail;
        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode.printLinkList(solution.reverse(ListNode.buildListNode(arr)));


    }

}
