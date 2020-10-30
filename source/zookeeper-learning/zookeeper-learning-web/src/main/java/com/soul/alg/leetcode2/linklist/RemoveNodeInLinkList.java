package com.soul.alg.leetcode2.linklist;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Objects;

/**
 * Write a function to delete a node in a singly-linked list.
 * You will not be given access to the head of the list,
 * instead you will be given access to the node to be deleted directly.
 * <p>
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 *
 * @author wangkunwk
 * @version 2020/9/28
 */
public class RemoveNodeInLinkList extends AbstractAlg {

    static class Solution {
        public void deleteNode(ListNode node) {
            if (Objects.isNull(node)) {
                return;
            }

            while (Objects.nonNull(node.next)) {
                node.val = node.next.val;
                if (node.next.next == null) {
                    node.next = null;
                    break;
                }
                node = node.next;
            }

        }
    }

    static class SolutionII {
        public void deleteNode(ListNode node) {
            if (Objects.isNull(node) || Objects.isNull(node.next)) {
                return;
            }

            node.val = node.next.val;
            node.next = node.next.next;

        }


    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 3, 5};
        ListNode listNode = ListNode.buildListNode(arr);

        ListNode pHead = new ListNode();
        pHead.next = listNode;

        while (listNode.val != 4) {
            listNode = listNode.next;
        }

        SolutionII solution = new SolutionII();
        solution.deleteNode(listNode);
        ListNode.printLinkList(pHead.next);
    }
}
