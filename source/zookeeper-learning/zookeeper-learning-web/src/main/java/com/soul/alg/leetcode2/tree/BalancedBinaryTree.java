package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the left and right subtrees of every
 * node differ in height by no more than 1.
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class BalancedBinaryTree extends AbstractAlg {

    static class Solution {

        private boolean result = true;

        public boolean isBalanced(TreeNode root) {

            if (Objects.isNull(root)) {
                return true;
            }

            int heightDiff = findHeightDiff(root, 0);

            return result;

        }

        private int findHeightDiff(TreeNode root, int height) {
            if (Objects.isNull(root)) {
                return height;
            }

            int left = findHeightDiff(root.left, height + 1);
            int right = findHeightDiff(root.right, height + 1);

            if (left > right) {
                if (left - right > 1) {
                    result = false;
                }
            } else {
                if (right - left > 1) {
                    result = false;
                }
            }

            return Math.max(left, right);
        }
    }

    static class SolutionII {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;

            if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) return false;

            return isBalanced(root.left) && isBalanced(root.right);
        }

        private int maxDepth(TreeNode root) {
            if (root == null) return 1;

            int left = maxDepth(root.left) + 1;
            int right = maxDepth(root.right) + 1;

            return Math.max(left, right);
        }


    }


}
