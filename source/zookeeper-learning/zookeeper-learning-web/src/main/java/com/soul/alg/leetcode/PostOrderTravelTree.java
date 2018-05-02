package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author wangkun1
 * @version 2018/5/2
 */
public class PostOrderTravelTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{1, -1, 2,-1,-1, 3}, -1);
        List<Integer> list = new First().postorderTraversal(root);
        System.out.println(list);
    }

    private static class First {
        List<Integer> list = new ArrayList<>();
        Map<TreeNode, Boolean> map = new HashMap<>();

        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return list;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            while (p != null || !stack.empty()) {

                if (map.get(p) == null) {
                    stack.push(p);
                }
                if (p.left != null && map.get(p.left) == null) {
                    p = p.left;
                    continue;
                }
                if (p.right != null && map.get(p.right) == null) {
                    p = p.right;
                    continue;
                }
                if (map.get(p) == null) {
                    list.add(p.val);
                    map.put(p, true);
                }
                if (!stack.empty()) {
                    p = stack.pop();
                } else {
                    p = null;
                }
            }
            return list;
        }
    }
}
