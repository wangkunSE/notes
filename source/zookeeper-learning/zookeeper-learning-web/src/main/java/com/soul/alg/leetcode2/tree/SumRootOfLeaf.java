package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree containing digits from 0-9 only,
 * each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which
 * represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Note: A leaf is a node with no children.
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class SumRootOfLeaf extends AbstractAlg {

    static class Solution {

        private int result = 0;

        public int sumNumbers(TreeNode root) {

            if (Objects.isNull(root)) {
                return result;
            }

            findAllPath(root, 0);

            return result;
        }

        private void findAllPath(TreeNode root, int curSum) {
            if (Objects.isNull(root)) {
                return;
            }
            curSum = curSum * 10 + root.val;
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                result += curSum;
                return;
            }

            findAllPath(root.left, curSum);
            findAllPath(root.right, curSum);

        }
    }
}
