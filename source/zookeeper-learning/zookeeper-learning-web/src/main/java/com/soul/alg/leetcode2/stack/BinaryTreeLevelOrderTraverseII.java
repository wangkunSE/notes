package com.soul.alg.leetcode2.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree, return the bottom-up level order
 * traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 *
 * @author wangkunwk
 * @version 2020/11/3
 */
public class BinaryTreeLevelOrderTraverseII extends AbstractAlg {

    static class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();
            if (Objects.isNull(root)) {
                return result;
            }

            Deque<TreeNode> queue = new ArrayDeque<>();
            Deque<List<Integer>> tempRes = new ArrayDeque<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                Deque<TreeNode> curLevelNode = new ArrayDeque<>();
                List<Integer> curLevelValue = new ArrayList<>();

                while (!queue.isEmpty()) {
                    curLevelNode.offer(queue.poll());
                }

                while (!curLevelNode.isEmpty()) {
                    TreeNode curNode = curLevelNode.poll();
                    curLevelValue.add(curNode.val);
                    if (Objects.nonNull(curNode.left)) {
                        queue.offer(curNode.left);
                    }

                    if (Objects.nonNull(curNode.right)) {
                        queue.offer(curNode.right);
                    }
                }
                tempRes.push(curLevelValue);

            }
            result.addAll(tempRes);

            return result;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.push(2);
        deque.push(3);

        List<Integer> result = new ArrayList<>(deque);
        System.out.println(result);
    }

}
