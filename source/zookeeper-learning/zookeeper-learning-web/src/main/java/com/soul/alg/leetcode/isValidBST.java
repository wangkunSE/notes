package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/3
 * @time: 下午2:14
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class isValidBST {
    public static void main(String[] args) {
//        First first = new First();
//        TreeNode treeNode = TreeNode.buildTree(new Integer[]{3, 1, 5, 0, 2, 4, 6}, -1);
        TreeNode root = TreeNode.buildTree(new Integer[]{10,5,15,-1,-1,6,20}, -1);
        Second second = new Second();
        boolean validBST1 = second.isValidBST(root);
        System.out.println(validBST1);
//        boolean validBST = first.isValidBST(treeNode);
//        System.out.println(validBST);
    }

    private static class First {
        public boolean isValidBST(TreeNode root) {
            if(root == null){
                return true;
            }
            return validLeft(root,root.left) && validRight(root,root.right) && isValidBST(root.left) && isValidBST(root.right);
        }

        private boolean validLeft(TreeNode root,TreeNode left){
            if(left == null){
                return true;
            }

            if(root.val <= left.val){
                return false;
            }

            return validLeft(root,left.left) && validLeft(root,left.right);
        }

        private boolean validRight(TreeNode root,TreeNode right){
            if(right == null){
                return true;
            }

            if(root.val >= right.val){
                return false;
            }

            return validRight(root,right.left) && validLeft(root,right.right);
        }
    }

    private static class Second {
        public boolean isValidBST(TreeNode root) {
            if(root == null){
                return true;
            }
            return check(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }

        private boolean check(TreeNode root,Integer min,Integer max){
            if(root == null){
                return true;
            }
            if(root.val <= min || root.val >= max){
                return false;
            }
            return check(root.left,min ,root.val) && check(root.right,root.val, max);
        }
    }
}
