package com.soul.alg.datastructure;

import java.util.Objects;

/**
 * @author wangkun1
 * @version 2018/4/18
 */
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildTree(Integer[] arrA, Integer reduceElement) {
        TreeNode[] root = new TreeNode[arrA.length];
        for (int i = 0; i < arrA.length; i++) {
            root[i] = new TreeNode(arrA[i], null, null);
        }
        for (int i = 0; i < (arrA.length / 2); i++) {
            root[i].left = root[2 * i + 1];
            if ((2 * i + 2) < arrA.length) {
                root[i].right = root[2 * i + 2];
            }
        }
        reduceNullNode(root[0], reduceElement);
        return root[0];
    }

    public static void reduceNullNode(TreeNode treeNode, Integer reduceElement) {
        if (treeNode != null) {
            if (treeNode.left != null && Objects.equals(treeNode.left.val, reduceElement)) {
                treeNode.left = null;
            }
            if (treeNode.right != null && Objects.equals(treeNode.right.val, reduceElement)) {
                treeNode.right = null;
            }
            reduceNullNode(treeNode.left, reduceElement);
            reduceNullNode(treeNode.right, reduceElement);
        }
    }

    public static void preOrderTravel(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preOrderTravel(root.left);
            preOrderTravel(root.right);
        }
    }
}