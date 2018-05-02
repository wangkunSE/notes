package com.soul.alg.sword;

import com.soul.alg.datastructure.TreeNode;

/**
 * @author wangkun1
 * @version 2018/4/18
 */
public class PrintImageTree {

    public static void main(String[] args) {
        int maxValue = Integer.MAX_VALUE;
        Integer[] arr = {8, 8, 7, 9, 2, maxValue, maxValue, maxValue, maxValue, 4, 7};
        TreeNode root = TreeNode.buildTree(arr, maxValue);
        new PrintImageTree().printImageTree(root);
        TreeNode.preOrderTravel(root);
    }

    private void printImageTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            printImageTree(root.left);
            printImageTree(root.right);
        }
    }
}
