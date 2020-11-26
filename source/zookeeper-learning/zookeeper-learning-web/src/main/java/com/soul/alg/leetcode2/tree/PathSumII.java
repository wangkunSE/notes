package com.soul.alg.leetcode2.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree and a sum, find all root-to-leaf
 * paths where each path's sum equals the given sum.
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
public class PathSumII extends AbstractAlg {
    static class Solution {

        private List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if (Objects.isNull(root)) {
                return result;
            }

            findPaths(root, sum, new ArrayList<>(), 1);
            return result;
        }

        private void findPaths(TreeNode root, int sum, List<Integer> tempResult, int level) {
            if (Objects.isNull(root)) {
                return;
            }
            tempResult.add(root.val);

            if (Objects.isNull(root.left)
                    && Objects.isNull(root.right)
                    && sum == root.val) {
                result.add(new ArrayList<>(tempResult));
                return;
            }

            findPaths(root.left, sum - root.val, tempResult, level + 1);
            if (tempResult.size() > level) {
                tempResult.remove(tempResult.size() - 1);
            }

            findPaths(root.right, sum - root.val, tempResult, level + 1);
            tempResult.remove(tempResult.size() - 1);
        }
    }


    //[5,4,8,11,null,13,4,7,2,null,null,5,1]
    //22
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.pathSum(
                        TreeNode.buildTree(
                                new Integer[]{5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, 5, 1},
                                -1
                        ),
                        22
                )
        );

    }
}
