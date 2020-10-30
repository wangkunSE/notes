package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * @author wangkunwk
 * @version 2020/9/22
 */
public class ReverseLinkListII extends AbstractAlg {

    static class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (Objects.isNull(head) || m > n) {
                return head;
            }

            int cnt = 1;
            ListNode p = head;
            ListNode pre = new ListNode();
            pre.next = head;
            while (cnt < m) {
                cnt++;
                if (cnt == m) {
                    pre = p;
                }
                p = p.next;
            }

            cnt = n - m + 1;
            ListNode tail = pre.next;
            pre.next = null;
            while (cnt > 0) {
                cnt--;
                ListNode r = p.next;
                p.next = pre.next;
                pre.next = p;
                p = r;
            }

            tail.next = p;

            return m == 1 ? pre.next : head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 2};
        ListNode.printLinkList(solution.reverseBetween(ListNode.buildListNode(arr), 1, 2));
    }
}
