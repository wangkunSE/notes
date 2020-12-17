package com.soul.alg.leetcode2.dp;

/**
 * Given an array prices and the i-th element of
 * it represents the price of a stock on the i-th day.
 * <p>
 * You may complete at most k transactions.
 * What's the maximum profit?
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: k = 2, prices = [4, 4, 6, 1, 1, 4, 2 ,5]
 * Output: 6
 * Explanation: Buy at 4 and sell at 6. Then
 * buy at 1 and sell at 5. Your profit is 2 + 4 = 6.
 * Example 2:
 * <p>
 * Input: k = 1, prices = [3, 2, 1]
 * Output: 0
 * Explanation: No transaction.
 * Challenge
 * O(nk) time. n is the size of prices.
 *
 * @author wangkunwk
 * @version 2020/12/1
 */
public class BestTimeBuySellStockIV {

    static class Solution {
        /**
         * @param K: An integer
         * @param A: An integer array
         * @return: Maximum profit
         */
        public int maxProfit(int K, int[] A) {
            // write your code here

            //f[i][j] = max{f[i-1][j],f[i-1][j-1] + P(i-1) - P(i-2)}
            //f[i][j] = max{f[i-1][j] + P(i-1) - P(i-2),f[i-1][j-1],f[i-1][j-2]+ P(i-1) - P(i-2)}
            if (A == null || A.length < 1 || K < 1) {
                return 0;
            }
            int n = A.length;


            if (K > n / 2) {
                int res = 0;
                for (int i = 1; i < A.length; i++) {
                    if (A[i] > A[i - 1]) {
                        res += (A[i] - A[i - 1]);
                    }
                }
                return res;
            }


            int[][] dp = new int[n + 1][2 * K + 2];
            dp[0][1] = 0;

            for (int i = 2; i <= 2 * K + 1; i++) {
                dp[0][i] = Integer.MIN_VALUE;
            }


            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < 2 * K + 2; j += 2) {
                    dp[i][j] = dp[i - 1][j];
                    if (i > 1 && j > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + A[i - 1] - A[i - 2]);
                    }
                }

                for (int j = 2; j < 2 * K + 1; j += 2) {
                    dp[i][j] = dp[i - 1][j - 1];
                    if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + A[i - 1] - A[i - 2]);
                    }

                    if (i > 2 && j > 2 && dp[i - 1][j - 2] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 2] + A[i - 1] - A[i - 2]);
                    }
                }

            }

            int res = 0;
            for (int i = 1; i < 2 * K + 2; i += 2) {
                res = Math.max(res, dp[n][i]);
            }

            return res;
        }
    }
}
