package com.soul.alg.leetcode2.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * 102. Binary Tree Level Order Traversal
 * Medium
 * <p>
 * 3670
 * <p>
 * 86
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class BinaryTreeLevelTraversal extends AbstractAlg {

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {

            ArrayList<List<Integer>> result = new ArrayList<>();
            if (Objects.isNull(root)) {
                return result;
            }

            LinkedList<TreeNode> listQueue = new LinkedList<>();
            listQueue.offerFirst(root);
            while (!listQueue.isEmpty()) {
                List<TreeNode> levelResult = new ArrayList<>();
                while (!listQueue.isEmpty()) {
                    TreeNode last = listQueue.removeLast();
                    levelResult.add(last);
                }
                List<Integer> curLevel = new ArrayList<>();
                for (TreeNode treeNode : levelResult) {
                    curLevel.add(treeNode.val);
                    if (treeNode.left != null) {
                        listQueue.offerFirst(treeNode.left);
                    }

                    if (treeNode.right != null) {
                        listQueue.offerFirst(treeNode.right);
                    }
                }

                result.add(curLevel);
            }
            return result;
        }
    }

    static class SolutionII {

        private List<List<Integer>> leverOrderList = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {

            levelOrder(root, 0);

            return leverOrderList;

        }

        private void levelOrder(TreeNode node, int level) {

            if (node == null)
                return;

            if (leverOrderList.size() < level + 1)
                leverOrderList.add(new ArrayList<>());

            leverOrderList.get(level).add(node.val);

            levelOrder(node.left, level + 1);
            levelOrder(node.right, level + 1);

        }
    }

    static class SolutionIII {

        public int maxDepth(TreeNode root) {


            return levelOrder(root, 0);

        }

        private int levelOrder(TreeNode root, int level) {

            if (root == null) {
                return level;
            }

            int left = levelOrder(root.left, level + 1);
            int right = levelOrder(root.right, level + 1);

            return Math.max(left, right);
        }
    }

    public static void main(String[] args) {
        SolutionIII solutionIII = new SolutionIII();


    }

}
