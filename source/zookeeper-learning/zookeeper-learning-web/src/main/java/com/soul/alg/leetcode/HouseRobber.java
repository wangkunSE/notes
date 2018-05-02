package com.soul.alg.leetcode;

import com.soul.alg.datastructure.TreeNode;

/**
 * @author wangkun1
 * @version 2018/5/2
 */
public class HouseRobber {

    public static void main(String[] args) {
//        int[] nums = {1};
//        int value = new CycleHouse().rob(nums);
        TreeNode root = TreeNode.buildTree(new Integer[]{1,2,3}, -1);
        int value = new TreeHouseDiscuss().rob(root);
        System.out.println(value);
    }

    private static class CycleHouse {
        public int rob(int[] nums) {

            int robFirst = 0;
            int notRobFirst = 0;
            int tempCurrentRob = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                tempCurrentRob = notRobFirst + nums[i];
                notRobFirst = Math.max(notRobFirst, robFirst);
                robFirst = tempCurrentRob;
            }
            int robFirstMax = Math.max(robFirst, notRobFirst);

            int robSecond = 0;
            int notRobSecond = 0;
            for (int i = 1; i < nums.length; i++) {
                tempCurrentRob = notRobSecond + nums[i];
                notRobSecond = Math.max(notRobSecond, robSecond);
                robSecond = tempCurrentRob;
            }
            int robSecondMax = Math.max(robSecond, notRobSecond);

            return Math.max(robFirstMax, robSecondMax);
        }
    }

    private static class TreeHouse {
        int rob = 0;
        int notRob = 0;

        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }

            robOrNot(root);
            return Math.max(rob, notRob);
        }

        public void robOrNot(TreeNode root) {
            if (root != null) {
                int tempRob = notRob + root.val;
                notRob = Math.max(rob, notRob);
                rob = tempRob;
                robOrNot(root.left);
                robOrNot(root.right);
            }
        }
    }

    private static class TreeHouseDiscuss {
        public int rob(TreeNode root) {
            int[] subValue = robSub(root);
            return Math.max(subValue[0], subValue[1]);
        }

        private int[] robSub(TreeNode root) {
            if (root == null) {
                return new int[2];
            }
            int[] left = robSub(root.left);
            int[] right = robSub(root.right);

            int[] res = new int[2];
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = root.val + left[0] + right[0];

            return res;
        }
    }
}
