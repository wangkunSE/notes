package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2019/10/9
 */
public class PathSum_112 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree(new Integer[]{5, 4, 8, 11, -1, 13, 4, 7, 2}, -1);
        TreeNode.preOrderTravel(treeNode);
        System.out.println(new PathSum_112().hasPathSum(treeNode, 22));
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (Objects.isNull(root)) {
            return false;
        }

        if (Objects.isNull(root.left) && Objects.isNull(root.right) && sum == root.val) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


}
