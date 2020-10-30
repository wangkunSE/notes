package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a linked list, rotate the list to the right by k places,
 * where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 * <p>
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class RotateList extends AbstractAlg {

    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {

            if (Objects.isNull(head) || Objects.isNull(head.next) || k < 1) {
                return head;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode p = head;
            int len = 0;
            while (Objects.nonNull(p)) {
                p = p.next;
                len++;
            }
            if (k > len) {
                k = k % len;
            }

            ListNode tail = null;
            ListNode fast = head;
            ListNode slow = pHead;

            while (Objects.nonNull(fast)) {
                if (Objects.isNull(fast.next)) {
                    tail = fast;
                }
                fast = fast.next;
                if (k <= 0) {
                    slow = slow.next;
                }

                k--;
            }

            tail.next = pHead.next;
            pHead.next = slow.next;
            slow.next = null;

            return pHead.next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{ 1, 2};
        ListNode.printLinkList(solution.rotateRight(ListNode.buildListNode(arr), 2));
    }
}
