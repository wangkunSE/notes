package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree, determine if it is a valid binary search
 * tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys
 * less than the node's key.
 * The right subtree of a node contains only nodes with keys
 * greater than the node's key.
 * Both the left and right subtrees must also
 * be binary search trees.
 * <p>
 * 10,5,15,null,null,6,20
 *
 * @author wangkunwk
 * @version 2020/11/19
 */
public class ValidBinarySearchTree extends AbstractAlg {

    static class Solution {
        public boolean isValidBST(TreeNode root) {
            return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean check(TreeNode root, long minValue, long maxValue) {
            if (Objects.isNull(root)) {
                return true;
            }

            if (root.val <= minValue || root.val >= maxValue) {
                return false;
            }
            return check(root.left, minValue, root.val) && check(root.right, root.val, maxValue);
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

/*
Key principle is that an inorder traversal will create a BST that is sorted.
so you can check if a BST is valid by doing inorder and making sure that the BST is always
greater than the min value seen so far and less than the max value seen so far (if doing it recursively)
Otherwise if you're doing it iteratively, you can just check that root.val > min so far. at every point.

*/

    static class SolutionII {
        public boolean isValidBST(TreeNode root) {
            if (root == null)
                return true;

            TreeNode[] prev = new TreeNode[1];

            return isValid(root, prev);
        }

        private boolean isValid(TreeNode root, TreeNode[] prev) {
            if (root == null)
                return true;

            boolean isLeftValid = isValid(root.left, prev);
            if (!isLeftValid)
                return false;

            if (prev[0] != null) {
                if (root.val <= prev[0].val)
                    return false;
            }
            prev[0] = root;

            return isValid(root.right, prev);
        }
    }


}
