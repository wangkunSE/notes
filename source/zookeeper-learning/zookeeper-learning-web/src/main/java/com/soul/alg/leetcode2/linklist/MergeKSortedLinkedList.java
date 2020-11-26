package com.soul.alg.leetcode2.linklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * @author wangkunwk
 * @version 2020/11/10
 */
public class MergeKSortedLinkedList extends AbstractAlg {

    //[[1,4,5],[1,3,4],[2,6]]
    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {

            if (Objects.isNull(lists) || lists.length < 1) {
                return null;
            }
            List<ListNode> nodeList = Arrays.asList(lists);

            while (nodeList.size() > 1) {
                List<ListNode> tmpList = new ArrayList<>();

                for (int i = 0; i < nodeList.size() / 2 + 1; i++) {

                    int left = 2 * i;
                    int right = left + 1;

                    if (left >= nodeList.size()) {
                        break;
                    }
                    ListNode leftNode = nodeList.get(left);
                    ListNode rightNode = null;
                    if (right < nodeList.size()) {
                        rightNode = nodeList.get(right);
                    }

                    tmpList.add(mergeTwoSortedList(leftNode, rightNode));

                }

                nodeList = new ArrayList<>(tmpList);
            }

            return nodeList.get(0);

        }

        private ListNode mergeTwoSortedList(ListNode leftNode, ListNode rightNode) {

            if (Objects.isNull(leftNode)) {
                return rightNode;
            }

            if (Objects.isNull(rightNode)) {
                return leftNode;
            }

            ListNode headNode = new ListNode();
            ListNode p = headNode;
            while (leftNode != null && rightNode != null) {

                if (leftNode.val > rightNode.val) {
                    p.next = rightNode;
                    rightNode = rightNode.next;
                } else {
                    p.next = leftNode;
                    leftNode = leftNode.next;
                }
                p = p.next;
            }

            if (leftNode == null) {
                p.next = rightNode;
            }
            if (rightNode == null) {
                p.next = leftNode;
            }

            return headNode.next;

        }
    }

    static class SolutionII {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0)
                return null;
            if (lists.length == 1)
                return lists[0];

            int i = 0, j = lists.length - 1;
            while (i < j) {
                lists[i] = mergeList(lists[i], lists[j]);
                i++;
                j--;

                if (i >= j)
                    i = 0;
            }

            return lists[0];
        }

        private ListNode mergeList(ListNode root1, ListNode root2) {
            ListNode root3 = new ListNode(0);
            ListNode root = root3;
            while (root1 != null && root2 != null) {
                if (root1.val < root2.val) {
                    root3.next = root1;
                    root1 = root1.next;
                } else {
                    root3.next = root2;
                    root2 = root2.next;
                }

                root3 = root3.next;
            }

            while (root1 != null) {
                root3.next = root1;
                root1 = root1.next;
                root3 = root3.next;
            }

            while (root2 != null) {
                root3.next = root2;
                root2 = root2.next;
                root3 = root3.next;
            }

            return root.next;
        }


    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        listNodes[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        listNodes[2] = new ListNode(2, new ListNode(6));

        ListNode listNode = solution.mergeKLists(listNodes);
        ListNode.printLinkList(listNode);
    }


}
