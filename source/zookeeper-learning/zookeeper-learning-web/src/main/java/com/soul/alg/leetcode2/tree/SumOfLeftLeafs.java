package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Find the sum of all left leaves in a given binary tree.
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class SumOfLeftLeafs extends AbstractAlg {

    //[3,9,20,null,null,15,7]
    static class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (Objects.isNull(root)) {
                return 0;
            }

            if (Objects.nonNull(root.left)
                    && Objects.isNull(root.left.left)
                    && Objects.isNull(root.left.right)) {
                return root.left.val + sumOfLeftLeaves(root.right);
            }

            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);

        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = TreeNode.buildTree(new Integer[]{3, 9, 20, -1, -1, 15, 7}, -1);
        System.out.println(solution.sumOfLeftLeaves(treeNode));
    }


}
