package com.soul.alg.leetcode2.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * @author wangkunwk
 * @version 2020/11/3
 */
public class BinaryPostOrderTraverse extends AbstractAlg {

    static class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (Objects.isNull(root)) {
                return result;
            }

            postorderTraversalRecurse(result, root);
            return result;

        }

        private void postorderTraversalRecurse(List<Integer> result, TreeNode root) {
            if (Objects.isNull(root)) {
                return;
            }

            postorderTraversalRecurse(result, root.left);
            postorderTraversalRecurse(result, root.right);
            result.add(root.val);

        }
    }
}
