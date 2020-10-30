package com.soul.alg.leetcode2.dp;

/**
 * Given a positive integer n, find the least
 * number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * @author wangkunwk
 * @version 2020/10/30
 */
public class PerfectSquares {

    static class Solution {
        public int numSquares(int n) {

            //f(x) = min{f(x-Aj)}+1
            if (n < 1) {
                return 0;
            }

            int[] dp = new int[n + 1];
            dp[1] = 1;

            for (int i = 2; i < n + 1; i++) {
                int min = Integer.MAX_VALUE - 1;
                for (int j = 1; j * j < i; j++) {
                    min = Math.min(min, dp[i - j * j] + 1);
                }
                dp[i] = min;
            }

            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(12));
    }
}
