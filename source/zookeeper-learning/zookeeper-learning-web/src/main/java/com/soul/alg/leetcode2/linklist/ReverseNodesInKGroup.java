package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Given a linked list, reverse the nodes of a linked list k at a
 * time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the
 * length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes,
 * only nodes itself may be changed.
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class ReverseNodesInKGroup extends AbstractAlg {

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (Objects.isNull(head) || k < 0) {
                return head;
            }

            ListNode pHead = new ListNode();
            ListNode p = pHead;
            p.next = head;
            ListNode pre = p;

            int length = 0;
            while (Objects.nonNull(p)) {
                length++;
                p = p.next;
            }
            p = head;

            length--;
            pre.next = null;
            if (length < k) {
                return head;
            }
            while (length >= k) {
                int cnt = k;
                ListNode tail = null;
                while (cnt > 0) {
                    ListNode r = p.next;
                    p.next = pre.next;
                    pre.next = p;
                    if (cnt == k) {
                        tail = p;
                    }
                    p = r;
                    cnt--;
                }
                pre = tail;
                length -= k;
            }
            if (Objects.nonNull(pre) && Objects.nonNull(p)) {
                pre.next = p;
            }

            return pHead.next;

        }
    }

    static class SolutionII {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode temp = new ListNode(0);
            return reverseKGroupRec( head, k);
        }

        public ListNode reverseKGroupRec(ListNode head, int k)
        {
            // temp = head;
            // if(head==null)
            // {
            //     return result;
            // }
            // ListNode temp = head;
            ListNode ptr = head;
            int noOfNodes = 0;
            while(ptr!=null && noOfNodes<k)
            {
                ptr = ptr.next;
                noOfNodes++;
            }
            if(noOfNodes==k)
            {
                ListNode prev = null;
                ListNode current = head;
                ListNode next = head;
                int count = 1;
                while(count <= k && current!=null)
                {
                    next = next.next;
                    current.next = prev;
                    prev = current;
                    current = next;
                    count++;
                }
                if(next != null)
                {
                    head.next = reverseKGroupRec(next, k);
                    // result = result.next;
                }
                return prev;
            }

            // else
            // {
            //      //head.next = temp;
            // }

            //result = result.next;
            // result.next =
            return head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 2};
        ListNode.printLinkList(solution.reverseKGroup(ListNode.buildListNode(arr), 2));
    }
}
