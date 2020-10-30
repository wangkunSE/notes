package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/28
 */
public class PalindromeLinkedList extends AbstractAlg {

    static class Solution {
        public boolean isPalindrome(ListNode head) {
            if (Objects.isNull(head) || Objects.isNull(head.next)) {
                return true;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode fast = head;
            ListNode slow = pHead;

            int cnt = -1;
            while (Objects.nonNull(fast) && Objects.nonNull(fast.next)) {
                fast = fast.next.next;
                slow = slow.next;
                cnt++;
            }

            ListNode tmp = slow;
            while (Objects.nonNull(tmp)) {
                tmp = tmp.next;
                cnt++;
            }

            ListNode newHead = new ListNode();
            ListNode p;
            if (cnt % 2 != 0) {
                p = slow.next.next;
            } else {
                p = slow.next;
            }
            newHead.next = null;
            slow.next = null;


            while (Objects.nonNull(p)) {
                ListNode r = p.next;
                p.next = newHead.next;
                newHead.next = p;
                p = r;
            }

            p = newHead.next;

            while (Objects.nonNull(head) && Objects.nonNull(p)) {
                if (head.val != p.val) {
                    return false;
                }
                head = head.next;
                p = p.next;
            }
            if (Objects.nonNull(head) || Objects.nonNull(p)) {
                return false;
            }

            return true;
        }
    }

    static class SolutionII {
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            ListNode p1 = head;
            ListNode p2 = head;
            ListNode p3 = p1.next;
            ListNode prev = p1;
            while (p2.next != null && p2.next.next != null) {
                p2 = p2.next.next;
                prev = p1;
                p1 = p3;
                p3 = p3.next;
                p1.next = prev;
            }

            if (p2.next == null) {
                p1 = p1.next;
            }

            while (p3 != null) {
                if (p3.val != p1.val) {
                    return false;
                }
                p1 = p1.next;
                p3 = p3.next;
            }
            return true;

        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        int[] arr = new int[]{0, 1, 3, 1, 0};
        System.out.println(solution.isPalindrome(ListNode.buildListNode(arr)));
    }

}
