package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

/**
 * @author wangkunwk
 * @version 2019/10/10
 */
public class PathSum_III_437 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree(new Integer[]{5, 4, 8, 11, -1, 13, 4, 7, 2}, -1);
        TreeNode.preOrderTravel(treeNode);
        System.out.println(new PathSum_III_437().pathSum(treeNode, 22));
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathForSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);

    }

    private int pathForSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (sum == root.val ? 1 : 0) + pathForSum(root.left, sum - root.val)
                + pathForSum(root.right, sum - root.val);
    }
}
