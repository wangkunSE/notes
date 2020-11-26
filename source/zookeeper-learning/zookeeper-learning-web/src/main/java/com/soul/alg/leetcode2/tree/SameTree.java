package com.soul.alg.leetcode2.tree;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given two binary trees, write a function to check if they
 * are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally
 * identical and the nodes have the same value.
 * <p>
 * Example 1:
 * <p>
 * Input:     1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * Output: true
 * Example 2:
 * <p>
 * Input:     1         1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * Output: false
 * Example 3:
 * <p>
 * Input:     1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * Output: false
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class SameTree extends AbstractAlg {

    static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && null != q) {
                return false;
            }

            if (p != null && q == null) {
                return false;
            }
            if (p == null) {
                return true;
            }

            if (p.val == q.val) {

                boolean left = isSameTree(p.left, q.left);
                boolean right = isSameTree(p.right, q.right);
                return left && right;
            }

            return false;
        }
    }

}
