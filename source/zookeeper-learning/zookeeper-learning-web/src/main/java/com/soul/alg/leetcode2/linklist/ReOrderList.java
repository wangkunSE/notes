package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes,
 * only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class ReOrderList extends AbstractAlg {

    static class Solution {
        public void reorderList(ListNode head) {
            if (Objects.isNull(head)) {
                return;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode fast = head;
            ListNode slow = head;

            while (Objects.nonNull(fast) && Objects.nonNull(fast.next)) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode p = pHead;
            ListNode newHead = slow.next;

            ListNode tmp = newHead;
            ListNode newPHead = new ListNode();
            newPHead.next = null;
            while (Objects.nonNull(tmp)) {
                ListNode r = tmp.next;
                tmp.next = newPHead.next;
                newPHead.next = tmp;
                tmp = r;
            }
            newHead = newPHead.next;
            slow.next = null;
            int cnt = 1;
            while (Objects.nonNull(head) && Objects.nonNull(newHead)) {
                if (cnt % 2 != 0) {
                    p.next = head;
                    head = head.next;
                } else {
                    p.next = newHead;
                    newHead = newHead.next;
                }
                p = p.next;
                cnt++;
            }

            if (Objects.nonNull(head)) {
                p.next = head;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 2, 3, 4,5};
        ListNode listNode = ListNode.buildListNode(arr);
        solution.reorderList(listNode);
        ListNode.printLinkList(listNode);
    }
}
