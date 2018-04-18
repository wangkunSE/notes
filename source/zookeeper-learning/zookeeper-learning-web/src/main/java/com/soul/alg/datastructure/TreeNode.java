package com.soul.alg.datastructure;

import java.util.Objects;

/**
 * @author wangkun1
 * @version 2018/4/18
 */
public class TreeNode {
    public Integer val;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode leftChild, TreeNode rightChild) {
        this.val = val;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public static TreeNode buildTree(Integer[] arrA, Integer reduceElement) {
        TreeNode[] root = new TreeNode[arrA.length];
        for (int i = 0; i < arrA.length; i++) {
            root[i] = new TreeNode(arrA[i], null, null);
        }
        for (int i = 0; i < (arrA.length / 2); i++) {
            root[i].leftChild = root[2 * i + 1];
            if ((2 * i + 2) < arrA.length) {
                root[i].rightChild = root[2 * i + 2];
            }
        }
        reduceNullNode(root[0], reduceElement);
        return root[0];
    }

    public static void reduceNullNode(TreeNode treeNode, Integer reduceElement) {
        if (treeNode != null) {
            if (treeNode.leftChild != null && Objects.equals(treeNode.leftChild.val, reduceElement)) {
                treeNode.leftChild = null;
            }
            if (treeNode.rightChild != null && Objects.equals(treeNode.rightChild.val, reduceElement)) {
                treeNode.rightChild = null;
            }
            reduceNullNode(treeNode.leftChild, reduceElement);
            reduceNullNode(treeNode.rightChild, reduceElement);
        }
    }
}