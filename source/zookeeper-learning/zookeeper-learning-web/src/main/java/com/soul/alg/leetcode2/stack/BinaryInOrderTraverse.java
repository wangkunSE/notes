package com.soul.alg.leetcode2.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * @author wangkunwk
 * @version 2020/11/3
 */
public class BinaryInOrderTraverse extends AbstractAlg {

    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (Objects.isNull(root)) {
                return result;
            }

            inorderTraversalRecurse(root, result);


            return result;

        }

        private void inorderTraversalRecurse(TreeNode root, List<Integer> result) {

            if (Objects.isNull(root)) {
                return;
            }

            inorderTraversalRecurse(root.left, result);
            result.add(root.val);
            inorderTraversalRecurse(root.right, result);

        }
    }
}
