package com.soul.alg.leetcode;

/**
 * @author wangkunwk
 * @version 2019/10/11
 */
public class MaxSubsetSum {

    public static void main(String[] args) {
        System.out.println(new MaxSubsetSum().getMaxSum(new int[]{-1, 5, -2, -2, -3, 1, 23, 2, 3, -9, 2, 1, 3, -10, 9, 2}));
    }

    public int getMaxSum(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int max = nums[0];
        int tempSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (tempSum < nums[i] && tempSum < 0) {
                tempSum = nums[i];
            } else {
                tempSum += nums[i];
            }
            if (tempSum > max) {
                max = tempSum;
            }
        }
        return max;
    }
}
