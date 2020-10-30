package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 *
 * @author wangkunwk
 * @version 2020/9/23
 */
public class PartitionList extends AbstractAlg {

    static class Solution {
        public ListNode partition(ListNode head, int x) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pre = new ListNode(), p = head;
            ListNode pHead = pre;
            pre.next = head;
            while (Objects.nonNull(pre.next)) {
                if (pre.next.val >= x) {
                    break;
                }
                pre = pre.next;
            }

            p = pre.next;

            ListNode tail = pre.next;
            pre.next = null;
            while (Objects.nonNull(p)) {
                while (Objects.nonNull(p.next) && p.next.val < x) {
                    ListNode tmp = p.next;
                    p.next = p.next.next;
                    tmp.next = null;
                    pre.next = tmp;
                    pre = pre.next;
                }
                p = p.next;
            }
            pre.next = tail;

            return pHead.next;

        }
    }

    static class SolutionII {
        public ListNode partition(ListNode head, int x) {
            if (head == null) return head;
            ListNode smallerHead = new ListNode(0);
            ListNode smaller = smallerHead;
            ListNode biggerHead = new ListNode(0);
            ListNode bigger = biggerHead;
            while (head != null) {
                if (head.val < x) {
                    smaller = smaller.next = head;
                } else {
                    bigger = bigger.next = head;
                }
                head = head.next;
            }

            smaller.next = biggerHead.next;
            bigger.next = null;
            return smallerHead.next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{3, 1};
        ListNode.printLinkList(solution.partition(ListNode.buildListNode(arr), 2));
    }
}
