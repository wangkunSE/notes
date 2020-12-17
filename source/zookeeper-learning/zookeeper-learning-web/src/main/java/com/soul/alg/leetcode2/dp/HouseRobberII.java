package com.soul.alg.leetcode2.dp;

/**
 * After robbing those houses on that street,
 * the thief has found himself a new place for his thievery s
 * o that he will not get too much attention.
 * This time, all houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, the security system for these houses
 * remain the same as for those in the previous street.
 * <p>
 * Given a list of non-negative integers representing
 * the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without
 * alerting the police.
 * <p>
 * Example
 * Example1
 * <p>
 * Input:  nums = [3,6,4]
 * Output: 6
 * Example2
 * <p>
 * Input:  nums = [2,3,2,3]
 * Output: 6
 * Notice
 * This is an extension of House Robber.
 *
 * @author wangkunwk
 * @version 2020/11/26
 */
public class HouseRobberII {

    static class Solution {
        /**
         * @param A: An array of non-negative integers.
         * @return: The maximum amount of money you can rob tonight
         */
        public int houseRobber2(int[] A) {
            // write your code here
            // f[i] = Math.max(f[i-2] + A[i-1],f[i-1]);

            if (A == null || A.length < 1) {
                return 0;
            }

            int[] dp = new int[A.length];
            dp[0] = 0;
            dp[1] = A[0];

            for (int i = 2; i < A.length; i++) {
                dp[i] = Math.max(dp[i - 2] + A[i - 1], dp[i - 1]);
            }

            int res = dp[A.length - 1];


            dp = new int[A.length + 1];
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = A[1];
            for (int i = 3; i < A.length + 1; i++) {
                dp[i] = Math.max(dp[i - 2] + A[i - 1], dp[i - 1]);
            }

            return Math.max(res, dp[A.length]);
        }
    }
}
