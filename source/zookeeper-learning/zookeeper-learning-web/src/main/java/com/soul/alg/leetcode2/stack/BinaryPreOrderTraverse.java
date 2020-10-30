package com.soul.alg.leetcode2.stack;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/10/11
 */
public class BinaryPreOrderTraverse extends AbstractAlg {
    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<>();
            if (Objects.isNull(root)) {
                return result;
            }

            addValues(root, result);
            return result;
        }

        private void addValues(TreeNode root, List<Integer> result) {
            if (Objects.isNull(root)) {
                return;
            }

            result.add(root.val);
            addValues(root.left, result);
            addValues(root.right, result);
        }
    }

}
