package com.soul.alg.leetcode2.tree;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h
 * nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 *
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class ComputeTreeNode extends AbstractAlg {

    class Solution {
        public int countNodes(TreeNode root) {
            return 1;
        }
    }

}
