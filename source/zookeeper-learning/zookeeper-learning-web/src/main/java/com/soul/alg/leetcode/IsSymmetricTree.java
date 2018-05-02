package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.Objects;

/**
 * @author wangkun1
 * @version 2018/4/26
 */
public class IsSymmetricTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{1,2,2,-1,3,-1,3},-1);
        boolean flag = new FirstTry().isSymmetric(root);
        System.out.println(flag);
    }

    private static class FirstTry {

        public boolean isSymmetric(TreeNode root) {
            if (root == null){
                return true;
            }
            return childIsSymmetric(root.left,root.right);
        }

        private boolean childIsSymmetric(TreeNode left, TreeNode right) {

            if((left == null && right != null) || (left != null && right == null)){
                return false;
            }
            if ( left == null ){
                return true;
            }
            if (!Objects.equals(left.val, right.val)){
                return false;
            }
            return childIsSymmetric(left.left,right.right) && childIsSymmetric(left.right,right.left);
        }
    }
}
