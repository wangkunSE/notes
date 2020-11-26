package com.soul.alg.leetcode2.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * Note: A leaf is a node with no children.
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class BinaryRootToLeaf extends AbstractAlg {

    class Solution {

        private List<String> path = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            if (Objects.isNull(root)) {
                return path;
            }
            findPath(root, new StringBuilder());
            return path;
        }

        private void findPath(TreeNode root, StringBuilder prePath) {
            if (Objects.isNull(root)) {
                return;
            }

            if ("".equals(prePath.toString())) {
                prePath.append(root.val);
            } else {
                prePath.append("->").append(root.val);
            }

            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                path.add(prePath.toString());
                return;
            }

            findPath(root.left, new StringBuilder(prePath));
            findPath(root.right, new StringBuilder(prePath));
        }
    }

    static class SolutionII {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            dfs(root, result, new StringBuilder());

            return result;
        }

        private void dfs(TreeNode root, List<String> result, StringBuilder sb) {
            if (root == null) {
                return;
            }

            int size = sb.length();
            sb.append(root.val);
            if (root.left == null && root.right == null) {
                result.add(sb.toString());
            } else {
                sb.append("->");
                dfs(root.left, result, sb);
                dfs(root.right, result, sb);
            }
            sb.setLength(size);
        }
    }


}
