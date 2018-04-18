package com.soul.alg.sword;

import java.util.Objects;

/**
 * @author wangkun1
 * @version 2018/4/17
 */
public class IsSubTree {

    private static class TreeNode {
        Integer val;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode() {
        }

        public TreeNode(int val, TreeNode leftChild, TreeNode rightChild) {
            this.val = val;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public static void main(String[] args) {
        Integer[] arrA = {8, 8, 7, 9, 2, -1, -1, -1, -1, 4, 7};
        Integer[] arrB = {2, 4, 7};
        IsSubTree isSubTree = new IsSubTree();
        TreeNode A = isSubTree.buildTree(arrA);
        TreeNode B = isSubTree.buildTree(arrB);
        boolean subTree = isSubTree.isSubTree(A, B);
        System.out.println(subTree);
    }

    private TreeNode buildTree(Integer[] arrA) {
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
        reduceNullNode(root[0]);
        return root[0];
    }

    private void reduceNullNode(TreeNode treeNode) {
        if (treeNode != null) {
            if (treeNode.leftChild != null && treeNode.leftChild.val == -1) {
                treeNode.leftChild = null;
            }
            if (treeNode.rightChild != null && treeNode.rightChild.val == -1) {
                treeNode.rightChild = null;
            }
            reduceNullNode(treeNode.leftChild);
            reduceNullNode(treeNode.rightChild);
        }
    }

    private boolean isSubTree(TreeNode source, TreeNode target) {
        boolean result = false;
        if (source != null && target != null) {
            if (Objects.equals(source.val, target.val)) {
                result = isChildStructure(source, target);
            }
            if (!result) {
                result = isSubTree(source.leftChild, target);
            }
            if (!result) {
                result = isSubTree(source.rightChild, target);
            }
        }
        return result;
    }

    private boolean isChildStructure(TreeNode source, TreeNode target) {
        if (target == null) {
            return true;
        }
        if (source == null) {
            return false;
        }
        return Objects.equals(source.val, target.val) && isChildStructure(source.leftChild, target.leftChild) &&
                isChildStructure(source.rightChild, target.rightChild);
    }
}
