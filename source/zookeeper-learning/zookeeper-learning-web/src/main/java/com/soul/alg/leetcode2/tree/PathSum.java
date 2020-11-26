package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree and a sum, determine if the tree
 * has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given the below binary tree and sum = 22,
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class PathSum extends AbstractAlg {

    static class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (Objects.isNull(root)) {
                return false;
            }

            if (Objects.isNull(root.left)
                    && Objects.isNull(root.right)
                    && root.val == sum) {
                return true;
            }

            return hasPathSum(root.left, sum - root.val)
                    || hasPathSum(root.right, sum - root.val);
        }
    }

}
