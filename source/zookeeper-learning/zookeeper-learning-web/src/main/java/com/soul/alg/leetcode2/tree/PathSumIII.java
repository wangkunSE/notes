package com.soul.alg.leetcode2.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * You are given a binary tree in which each node contains
 * an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes
 * to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are
 * in the range -1,000,000 to 1,000,000.
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class PathSumIII extends AbstractAlg {

    static class Solution {

        List<List<Integer>> allPaths = new ArrayList<>();

        public int pathSum(TreeNode root, int sum) {

            if (Objects.isNull(root)) {
                return 0;
            }

            findAllPath(root, new ArrayList<Integer>(), 1);

            int result = 0;
            for (List<Integer> curPath : allPaths) {
                for (int i = 0; i < curPath.size(); i++) {
                    int curSum = curPath.get(i);
                    if (curSum == sum) {
                        result += 1;
                    }
                    for (int j = i + 1; j < curPath.size(); j++) {
                        curSum += curPath.get(j);
                        if (curSum == sum) {
                            result += 1;
                        }
                    }
                }
            }
            return result;
        }

        private void findAllPath(TreeNode root, List<Integer> tmpPath, int level) {
            if (Objects.isNull(root)) {
                return;
            }

            tmpPath.add(root.val);
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                allPaths.add(new ArrayList<>(tmpPath));
                return;
            }

            findAllPath(root.left, tmpPath, level + 1);
            if (level < tmpPath.size()) {
                tmpPath.remove(tmpPath.size() - 1);
            }

            findAllPath(root.right, tmpPath, level + 1);
            tmpPath.remove(tmpPath.size() - 1);
        }
    }

    static class SolutionII {
        public int pathSum(TreeNode root, int sum) {
            HashMap<Integer, Integer> map = new HashMap();
            map.put(0, 1);
            return find(root, 0, sum, map);
        }

        public int find(TreeNode root, int curr, int sum, HashMap<Integer, Integer> map) {
            if (root == null) return 0;

            curr += root.val;

            int result = map.getOrDefault(curr - sum, 0); // curr - sum is the excess amount
            int count = map.getOrDefault(curr, 0);

            map.put(curr, count + 1);
            result += find(root.left, curr, sum, map) + find(root.right, curr, sum, map);
            map.put(curr, count);

            return result;
        }
    }

    /**
     * So the idea is similar as Two sum, using HashMap to store
     * ( key : the prefix sum, value : how many ways get to this prefix sum) ,
     * and whenever reach a node, we check if prefix sum - target exists in hashmap or not,
     * if it does, we added up the ways of prefix sum - target into res.
     * <p>
     * For instance : in one path we have 1,2,-1,-1,2,
     * then the prefix sum will be: 1, 3, 2, 1, 3,
     * let's say we want to find target sum is 2,
     * then we will have{2}, {1,2,-1}, {2,-1,-1,2}
     * and {2}ways.
     * <p>
     * I used global variable count, but obviously we can avoid global variable by passing the count from bottom up.
     * The time complexity is O(n). This is my first post in discuss, open to any improvement or criticism. :)
     * <p>
     * <p>
     * <p>
     * This is an excellent idea and took me some time to figure out the logic behind.
     * Hope my comment below could help some folks better understand this solution.
     * <p>
     * The prefix stores the sum from the root to the current node in the recursion
     * The map stores <prefix sum, frequency> pairs before getting to the current node.
     * We can imagine a path from the root to the current node. The sum from any node in the middle of the path to
     * the current node = the difference between the sum from the root to the current node and the prefix sum of
     * the node in the middle.
     * We are looking for some consecutive nodes that sum up to the given target value,
     * which means the difference discussed in 2. should equal to the target value. In addition,
     * we need to know how many differences are equal to the target value.
     * <p>
     * Here comes the map. The map stores the frequency of all possible sum in the path to the current node.
     * If the difference between the current sum and the target value exists in the map,
     * there must exist a node in the middle of the path, such that from this node to the current node,
     * the sum is equal to the target value.
     * Note that there might be multiple nodes in the middle that satisfy what is discussed in 4.
     * The frequency in the map is used to help with this.
     * Therefore, in each recursion, the map stores all information we need to calculate the number
     * of ranges that sum up to target. Note that each range starts from a middle node, ended by the current node.
     * To get the total number of path count, we add up the number of valid paths ended by EACH node in the tree.
     * Each recursion returns the total count of valid paths in the subtree rooted at the current node.
     * And this sum can be divided into three parts:
     * - the total number of valid paths in the subtree rooted at the current node's left child
     * - the total number of valid paths in the subtree rooted at the current node's right child
     * - the number of valid paths ended by the current node
     * The interesting part of this solution is that the prefix is counted from the top(root) to the bottom(leaves),
     * and the result of total count is calculated from the bottom to the top :D
     * <p>
     * The code below takes 16 ms which is super fast.
     */

    static class SolutionIII {


        int res = 0;
        Map<Integer, Integer> map = new HashMap();
        int k = 0;
        int currSum = 0;

        public int pathSum(TreeNode root, int k) {
            this.k = k;
            map.put(0, 1);
            pathSum(root);
            return res;
        }

        private void pathSum(TreeNode root) {
            if (root == null) return;
            currSum += root.val;
            if (map.containsKey(currSum - k)) res += map.get(currSum - k);
            if (!map.containsKey(currSum)) map.put(currSum, 0);
            map.put(currSum, map.get(currSum) + 1);
            pathSum(root.left);
            pathSum(root.right);
            map.put(currSum, map.get(currSum) - 1);
            currSum -= root.val;

        }
    }


    static class SolutionIV {
        public int pathSum(TreeNode root, int sum) {
            HashMap<Integer, Integer> preSum = new HashMap();
            preSum.put(0, 1);
            helper(root, 0, sum, preSum);
            return count;
        }

        int count = 0;

        public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
            if (root == null) {
                return;
            }

            currSum += root.val;

            if (preSum.containsKey(currSum - target)) {
                count += preSum.get(currSum - target);
            }

            if (!preSum.containsKey(currSum)) {
                preSum.put(currSum, 1);
            } else {
                preSum.put(currSum, preSum.get(currSum) + 1);
            }

            helper(root.left, currSum, target, preSum);
            helper(root.right, currSum, target, preSum);
            preSum.put(currSum, preSum.get(currSum) - 1);
        }
    }

    static class SolutionV {
        private int count = 0;

        public int pathSum(TreeNode root, int sum) {
            if (Objects.isNull(root)) {
                return 0;
            }
            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);
            findAllSum(root, preSum, sum, 0);
            return count;

        }

        private void findAllSum(TreeNode root, Map<Integer, Integer> preSum, int sum, int curSum) {

            if (Objects.isNull(root)) {
                return;
            }

            curSum += root.val;
            if (preSum.containsKey(curSum - sum)) {
                count += preSum.get(curSum - sum);
            }

            preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);

            findAllSum(root.left, preSum, sum, curSum);
            findAllSum(root.right, preSum, sum, curSum);

            preSum.put(curSum, preSum.get(curSum) - 1);


        }
    }

    static class SolutionVI {
        public int pathSum(TreeNode root, int sum) {
            if (root == null) {
                return 0;
            }

            return pathForSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);

        }

        private int pathForSum(TreeNode root, int sum) {
            if (root == null) {
                return 0;
            }
            return (sum == root.val ? 1 : 0) + pathForSum(root.left, sum - root.val)
                    + pathForSum(root.right, sum - root.val);
        }
    }


    public static void main(String[] args) {
        //[10,5,-3,3,2,null,11,3,-2,null,1]
        //8

        SolutionIII solution = new SolutionIII();
        int sum = solution.pathSum(TreeNode.buildTree(new Integer[]{10, 5, -3, 3, 2, -1, 11, 3, -2, -1, 1}, -1), 8);
        System.out.println(sum);


    }
}
