package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wangkunwk
 * @version 2019/10/12
 */
public class MaximumWidthOfBinaryTree_622 {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree(new Integer[]{1, 3, 2, 5, 3, -1, 9}, -1);
//        TreeNode.preOrderTravel(treeNode);
        System.out.println(new BFSSolution().widthOfBinaryTree(treeNode));
    }

    private static class DFSSolution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            List<Integer> start = new LinkedList<>();
            List<Integer> end = new LinkedList<>();

            return findMaxWidthOfBinaryTree(root, 0, 1, start, end);
        }

        private int findMaxWidthOfBinaryTree(TreeNode root, int level, int order, List<Integer> start, List<Integer> end) {
            if (root == null) {
                return 0;
            }
            if (start.size() == level) {
                start.add(order);
                end.add(order);
            } else {
                end.set(level, order);
            }
            int cur = end.get(level) - start.get(level) + 1;
            int left = findMaxWidthOfBinaryTree(root.left, level + 1, 2 * order, start, end);
            int right = findMaxWidthOfBinaryTree(root.right, level + 1, 2 * order + 1, start, end);

            return Math.max(cur, Math.max(left, right));
        }
    }

    private static class BFSSolution {

        private static final Map<TreeNode, Integer> nodeOrderMap = new HashMap<>();

        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            nodeOrderMap.put(root, 1);
            queue.offer(root);
            int maxWidth = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                maxWidth = Math.max(nodeOrderMap.get(queue.peekLast()) - nodeOrderMap.get(queue.peekFirst()) + 1, maxWidth);
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = queue.poll();
                    if (tempNode.left != null) {
                        queue.offer(tempNode.left);
                        nodeOrderMap.put(tempNode.left, nodeOrderMap.get(tempNode) * 2);
                    }

                    if (tempNode.right != null) {
                        queue.offer(tempNode.right);
                        nodeOrderMap.put(tempNode.right, nodeOrderMap.get(tempNode) * 2 + 1);
                    }

                }


            }

            return maxWidth;
        }
    }


}
