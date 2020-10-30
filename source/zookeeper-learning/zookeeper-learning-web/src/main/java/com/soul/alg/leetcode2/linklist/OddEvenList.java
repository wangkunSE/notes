package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking
 * about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 *
 * @author wangkunwk
 * @version 2020/9/23
 */
public class OddEvenList extends AbstractAlg {

    static class Solution {
        public ListNode oddEvenList(ListNode head) {

            if (Objects.isNull(head)) {
                return head;
            }

            ListNode even = new ListNode();
            ListNode odd = new ListNode();
            ListNode hEven = even;
            ListNode hOdd = odd;
            ListNode p = head;

            int index = 1;

            while (Objects.nonNull(p)) {

                if (index % 2 == 0) {
                    odd.next = p;
                    odd = odd.next;
                } else {
                    even.next = p;
                    even = even.next;
                }
                index++;
                p = p.next;

            }
            even.next = hOdd.next;
            odd.next = null;
            return hEven.next;

        }

    }

    public static void main(String[] args) {

        System.out.println(1%2);
        Solution solution = new Solution();
        int[] arr = new int[]{1,2,3,4,5};
        ListNode.printLinkList(solution.oddEvenList(ListNode.buildListNode(arr)));
    }

}
