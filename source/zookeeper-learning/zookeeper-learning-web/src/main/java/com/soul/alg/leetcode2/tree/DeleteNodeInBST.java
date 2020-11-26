package com.soul.alg.leetcode2.tree;

import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a root node reference of a BST and a key, delete
 * the node with the given key in the BST. Return the root
 * node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 * @author wangkunwk
 * @version 2020/11/19
 */
public class DeleteNodeInBST extends AbstractAlg {

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {

            if (Objects.isNull(root)) {
                return root;
            }

            TreeNode keyNode = findAndDeleteNode(root, key);

            return root;

        }

        private TreeNode findAndDeleteNode(TreeNode root, int key) {

            if (Objects.isNull(root)) {
                return null;
            }
            if (root.val == key) {
                if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                    return root;
                } else if (Objects.isNull(root.left)) {
                    root.val = root.right.val;
                    root.right = null;
                } else if (Objects.isNull(root.right)) {
                    root.val = root.left.val;
                    root.left = null;
                } else {
                    root.val = root.left.val;
                    root.left = null;
                }
            } else if (root.val > key) {
                findAndDeleteNode(root.left, key);
            } else {
                findAndDeleteNode(root.right, key);
            }
            return null;
        }
    }
}
