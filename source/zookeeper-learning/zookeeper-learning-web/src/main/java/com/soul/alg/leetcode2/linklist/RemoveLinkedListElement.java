package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 *
 *
 * @author wangkunwk
 * @version 2020/9/26
 */
public class RemoveLinkedListElement extends AbstractAlg {

    static class Solution {
        public ListNode removeElements(ListNode head, int val) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pHead = new ListNode();
            pHead.next = head;
            ListNode p = pHead;

            while (Objects.nonNull(head)) {
                if (head.val != val) {
                    p.next = head;
                    p = p.next;
                }
                head = head.next;
            }

            p.next = head;

            return pHead.next;
        }
    }
}
