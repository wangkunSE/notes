package com.soul.alg.sword;

import com.soul.alg.datastructure.TreeNode;

import java.util.Objects;

/**
 * @author wangkun1
 * @version 2018/4/17
 */
public class IsSubTree {


    public static void main(String[] args) {
        Integer[] arrA = {8, 8, 7, 9, 2, -1, -1, -1, -1, 4, 7};
        Integer[] arrB = {2, 4, 7};
        IsSubTree isSubTree = new IsSubTree();
        TreeNode A = TreeNode.buildTree(arrA, -1);
        TreeNode B = TreeNode.buildTree(arrB, -1);
        boolean subTree = isSubTree.isSubTree(A, B);
        System.out.println(subTree);
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
