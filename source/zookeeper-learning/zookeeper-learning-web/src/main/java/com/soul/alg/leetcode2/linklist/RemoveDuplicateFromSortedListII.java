package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * <p>
 * Return the linked list sorted as well.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 *
 * @author wangkunwk
 * @version 2020/9/27
 */
public class RemoveDuplicateFromSortedListII extends AbstractAlg {

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode p = pHead;

            while (Objects.nonNull(head)) {
                if (Objects.nonNull(head.next)) {
                    if (head.val == head.next.val) {
                        while (Objects.nonNull(head) && Objects.nonNull(head.next) && head.val == head.next.val) {
                            head = head.next;
                        }
                    } else {
                        p.next = head;
                        p = p.next;
                    }
                    head = head.next;
                } else {
                    p.next = head;
                    p = p.next;
                    head = null;
                }
            }
            p.next = null;


            return pHead.next;
        }
    }


    static class SolutionII {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode dummy = new ListNode(Integer.MAX_VALUE);
            dummy.next = head;
            ListNode prev = dummy;
            ListNode curr = head;
            ListNode next = null;
            while (curr != null && curr.next != null) {
                next = curr.next;
                while (next != null && curr.val == next.val) {
                    next = next.next;
                }

                if (curr.next != next) {
                    prev.next = next;
                } else {
                    prev = curr;
                }
                curr = next;
            }

            return dummy.next;

        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        int[] arr = new int[]{1, 2,3,3,4,4,5};
        ListNode.printLinkList(solution.deleteDuplicates(ListNode.buildListNode(arr)));
    }
}
