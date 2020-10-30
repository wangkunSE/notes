package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Merge two sorted linked lists and return it as a new sorted list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * @author wangkunwk
 * @version 2020/9/27
 */
public class MergedTwoSortedLinkList extends AbstractAlg {
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (Objects.isNull(l1)) {
                return l2;
            }
            if (Objects.isNull(l2)) {
                return l1;
            }

            ListNode pHead = new ListNode();
            ListNode p = pHead;

            while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }

            if (Objects.isNull(l1)) {
                p.next = l2;
            } else {
                p.next = l1;
            }

            return pHead.next;
        }
    }

    static class SolutionII {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (Objects.isNull(l1)) {
                return l2;
            }
            if (Objects.isNull(l2)) {
                return l1;
            }

            if (l1.val > l2.val) {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            } else {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
        }
    }

}
