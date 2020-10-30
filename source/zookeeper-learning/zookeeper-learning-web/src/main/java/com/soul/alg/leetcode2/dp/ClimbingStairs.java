package com.soul.alg.leetcode2.dp;

/**
 * 70. Climbing Stairs
 * <p>
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * @author wangkunwk
 * @version 2020/10/27
 */
public class ClimbingStairs {

    static class Solution {
        public int climbStairs(int n) {
            if (n < 1) {
                return 0;
            }

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        for (int i = 0; i < 10; i++) {
            System.out.println(solution.climbStairs(i));
        }

    }


}
