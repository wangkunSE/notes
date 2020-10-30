package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Sort a linked list using insertion sort.
 * <p>
 * <p>
 * A graphical example of insertion sort.
 * The partial sorted list (black) initially contains only the first element
 * in the list.
 * With each iteration one element (red) is removed from the i
 * nput data and inserted in-place into the sorted list
 * <p>
 * <p>
 * Algorithm of Insertion Sort:
 * <p>
 * Insertion sort iterates, consuming one input element
 * each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one
 * element from the input data, finds the location
 * it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class SortedALinkedList extends AbstractAlg {
    static class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (Objects.isNull(head)) {
                return head;
            }

            ListNode pHead = new ListNode(Integer.MIN_VALUE);
            pHead.next = head;
            ListNode p = head;
            ListNode pre = pHead;


            while (Objects.nonNull(p)) {
                if (Objects.nonNull(p.next)) {
                    if (p.val > p.next.val) {
                        ListNode tail = p;
                        ListNode curr = p.next;
                        p = pHead.next;
                        while (Objects.nonNull(p) && p.val < curr.val) {
                            if (pre.val < curr.val) {
                                pre = p;
                            }
                            p = p.next;
                        }

                        tail.next = curr.next;
                        curr.next = pre.next;
                        pre.next = curr;
                        pre = pHead;
                        p = tail;
                    } else {
                        p = p.next;
                    }
                } else {
                    p = null;
                }

            }
            return pHead.next;
        }
    }

    static class SolutionII {
        public ListNode insertionSortList(ListNode head) {
            if(head==null || head.next==null) return head;
            ListNode midNode = mid(head);
            ListNode nHead= midNode.next;
            midNode.next=null;

            return mergerLinkedList(insertionSortList(head),insertionSortList(nHead));
        }

        public ListNode mergerLinkedList(ListNode l1 ,ListNode l2){
            if(l1==null && l2==null) return null;
            if(l1==null) return l2;
            if(l2==null) return l1;

            ListNode curr1=l1;
            ListNode curr2=l2;
            ListNode head= new ListNode(-1);
            ListNode res=head;
            while(l1!=null && l2!=null){
                if(l1.val<l2.val){
                    res.next=l1;
                    res=l1;
                    l1=l1.next;
                }else{
                    res.next=l2;
                    res=l2;
                    l2=l2.next;
                }
            }
            if(l1!=null) res.next=l1;
            if(l2!=null) res.next=l2;

            return head.next;
        }
        public ListNode mid(ListNode head){
            if(head==null || head.next==null) return head;

            ListNode slow=head;
            ListNode fast= head;

            while(fast.next!=null && fast.next.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }
            return slow;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{-1, 5, 2, 1, 3, 0};
        ListNode.printLinkList(solution.insertionSortList(ListNode.buildListNode(arr)));
    }
}
