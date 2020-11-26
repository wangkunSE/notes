package com.soul.alg.leetcode2.tree;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree, check whether it is a mirror
 * of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * <p>
 * Follow up: Solve it both recursively and iteratively.
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class SymmetricTree extends AbstractAlg {

    static class Solution {


        public boolean isSymmetric(TreeNode root) {

            if (root == null) {
                return true;
            }

            return isSymmetricLeftAndRight(root.left, root.right);
        }

        private boolean isSymmetricLeftAndRight(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            return isSymmetricLeftAndRight(left.right, right.left)
                    && isSymmetricLeftAndRight(left.left, right.right);
        }


    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Integer[] arr = new Integer[]{1, 2, 2, -1, 3, -1, 3};

        boolean symmetric = solution.isSymmetric(TreeNode.buildTree(arr, -1));
        System.out.println(symmetric);
    }
}
