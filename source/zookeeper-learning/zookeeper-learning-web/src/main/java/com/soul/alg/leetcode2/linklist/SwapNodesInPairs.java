package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the list's nodes,
 * only nodes itself may be changed.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class SwapNodesInPairs extends AbstractAlg {
    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pHead = new ListNode();
            ListNode p = pHead;
            p.next = head;

            while (Objects.nonNull(p)) {
                if (Objects.nonNull(p.next) && Objects.nonNull(p.next.next)) {
                    ListNode first = p.next;
                    ListNode second = p.next.next;
                    first.next = second.next;
                    p.next = second;
                    second.next = first;
                    p = first;
                } else {
                    p = p.next;
                }
            }

            return pHead.next;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 2, 3, 4};
        ListNode.printLinkList(solution.swapPairs(ListNode.buildListNode(arr)));
    }
}
