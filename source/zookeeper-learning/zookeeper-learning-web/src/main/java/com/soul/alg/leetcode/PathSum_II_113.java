package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2019/10/10
 */
public class PathSum_II_113 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree(new Integer[]{-2, -1, -3}, -1);
        System.out.println(new PathSum_II_113().pathSum(treeNode, -5));
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (null == root) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        findAllPath(result, root, sum, new ArrayList<>());
        return result;
    }

    private void findAllPath(List<List<Integer>> result, TreeNode root, int sum, List<Integer> tempSum) {
        if (Objects.isNull(root)) {
            return;
        }


        if (Objects.isNull(root.left) && Objects.isNull(root.right) && sum == root.val) {
            tempSum.add(root.val);
            result.add(new ArrayList<>(tempSum));
            tempSum.remove(tempSum.size() - 1);
            return;
        }

        tempSum.add(root.val);
        findAllPath(result, root.left, sum - root.val, tempSum);
//        tempSum.remove(tempSum.size() - 1);
//        tempSum.add(root.val);
        findAllPath(result, root.right, sum - root.val, tempSum);
        tempSum.remove(tempSum.size() - 1);
    }
}
