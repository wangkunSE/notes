package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a linked list, remove the n-th node from the end of
 * list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked
 * list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class RemoveNthNodeOfList extends AbstractAlg {

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (Objects.isNull(head) || n < 1) {
                return head;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode fast = head;
            ListNode slow = pHead;
            while (Objects.nonNull(fast)) {
                fast = fast.next;
                if (n <= 0) {
                    slow = slow.next;
                }
                n--;
            }

            if (Objects.nonNull(slow.next)) {
                slow.next = slow.next.next;
            }
            return pHead.next;

        }
    }
}
