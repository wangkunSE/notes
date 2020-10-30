package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/24
 */
public class AddTwoNumbersII extends AbstractAlg {

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            if (Objects.isNull(l1) || Objects.isNull(l2)) {
                return null;
            }

            ListNode head = new ListNode();

            int len1 = 0;
            int len2 = 0;
            ListNode p = l1;
            while (Objects.nonNull(p)) {
                len1++;
                p = p.next;
            }

            p = l2;
            while (Objects.nonNull(p)) {
                len2++;
                p = p.next;
            }

            if (len1 < len2) {
                p = l1;
                l1 = l2;
                l2 = p;

                len1 = len1 + len2;
                len2 = len1 - len2;
                len1 = len1 - len2;
            }

            int result = calcSum(l1, l2, len1 - len2);

            if (result > 0) {
                return new ListNode(result, l1);
            }

            return l1;
        }

        private int calcSum(ListNode l1, ListNode l2, int diff) {

            if (Objects.isNull(l1)) {
                return 0;
            }
            int sum = 0;
            if (diff > 0) {
                sum = l1.val + calcSum(l1.next, l2, diff - 1);
            } else {
                sum = l1.val + l2.val + calcSum(l1.next, l2.next, diff);
            }
            l1.val = sum % 10;
            return sum / 10;
        }


        public ListNode reverse(ListNode head) {
            if (Objects.isNull(head) || Objects.isNull(head.next)) {
                return head;
            }
            ListNode tail = reverse(head.next);
            head.next.next = head;
            head.next = null;

            return tail;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{7, 2, 4, 3};
        int[] arr2 = new int[]{5, 6, 4};
        ListNode.printLinkList(solution.addTwoNumbers(ListNode.buildListNode(arr), ListNode.buildListNode(arr2)));
    }


}
