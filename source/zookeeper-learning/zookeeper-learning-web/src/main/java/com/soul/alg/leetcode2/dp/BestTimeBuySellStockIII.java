package com.soul.alg.leetcode2.dp;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * Say you have an array for which the ith element
 * is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit.
 * You may complete at most two transactions.
 * <p>
 * Example
 * Example 1
 * <p>
 * Input : [4,4,6,1,1,4,2,5]
 * Output : 6
 * Notice
 * You may not engage in multiple transactions
 * at the same time (ie, you must sell the stock
 * before you buy again).
 *
 * @author wangkunwk
 * @version 2020/12/1
 */
public class BestTimeBuySellStockIII extends AbstractAlg {

    static class Solution {
        /**
         * @param A: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int[] A) {
            // write your code here
            // f[i][j] = max{f[i-1][j],f[i-1][j-1] + P(i-1) - P(i-2)}
            // f[i][j] = max{f[i-1][j]+P(i-1) - P(i-2),f[i-1][j-1], f[i-1][j-2] + P(i-1) - P(i-2)}

            if(A == null || A.length < 1){
                return 0;
            }


            int n = A.length + 1;
            int[][] dp = new int[n][6];

            int i, j, k;
            dp[0][0] = 0;
            dp[0][1] = dp[0][2] = dp[0][3] = dp[0][4] = dp[0][5] = Integer.MIN_VALUE;

            for (i = 1; i < n; i++) {

                for (j = 1; j <= 5; j += 2) {
                    dp[i][j] = dp[i - 1][j];
                    if (i > 1 && j > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + A[i - 1] - A[i - 2]);
                    }
                }

                for (j = 2; j <= 5; j += 2) {
                    dp[i][j] = dp[i - 1][j - 1];
                    if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + A[i - 1] - A[i - 2]);
                    }

                    if (i > 1 && j > 2 && dp[i - 1][j - 2] != Integer.MIN_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 2] + A[i - 1] - A[i - 2]);
                    }

                }

            }

            return Math.max(dp[n - 1][1], Math.max(dp[n - 1][3], dp[n - 1][5]));
        }
    }


}
