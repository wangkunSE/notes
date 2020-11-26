package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a binary search tree (BST), find the lowest common
 * ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p and
 * q as descendants (where we allow a node to be a descendant of itself).”
 *
 * @author wangkunwk
 * @version 2020/11/18
 */
public class LowestCommonAncestor extends AbstractAlg {

    class Solution {

        private TreeNode ancestor = null;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (Objects.isNull(root)) {
                return null;
            }
            if (p.val > q.val) {
                lowestCommonAncestor(root, q, p);
            }

            TreeNode ancestor = findLowestCommonAncestor(root, p, q);
            return ancestor;

        }

        private TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (Objects.isNull(root)) {
                return root;
            }
            if (root == p || root == q) {
                return root;
            }

            TreeNode left = findLowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
            TreeNode right = findLowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);

            if (left == p && right == q) {
                ancestor = root;
                return root;
            } else if (left == p) {
                return p;
            } else if (right == q) {
                return q;
            } else {
                return null;
            }
        }
    }

    static class SolutionII {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor(root.left, p, q);
            } else if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }
        }
    }


}
