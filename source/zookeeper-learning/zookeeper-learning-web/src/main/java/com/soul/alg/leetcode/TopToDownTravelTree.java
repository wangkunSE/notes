package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wangkun1
 * @version 2018/4/24
 */
public class TopToDownTravelTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{8,6,10,5,7,9,11}, -1);
        new TopToDownTravelTree().topToDownTravelTree(root);
    }

    private void topToDownTravelTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode temp = queue.poll();
            System.out.println(temp.val);
            if (temp.leftChild != null) {
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null) {
                queue.add(temp.rightChild);
            }
        }
    }
}
