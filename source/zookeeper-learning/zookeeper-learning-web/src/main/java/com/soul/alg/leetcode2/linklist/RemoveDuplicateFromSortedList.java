package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/23
 */
public class RemoveDuplicateFromSortedList extends AbstractAlg {

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pre = head;
            ListNode p = head.next;

            while (Objects.nonNull(p)) {
                if (pre.val == p.val) {
                    pre.next = p.next;
                } else {
                    pre = p;
                }
                p = p.next;
            }

            return head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 1, 2, 3, 3};
        ListNode.printLinkList(solution.deleteDuplicates(ListNode.buildListNode(arr)));
    }
}
