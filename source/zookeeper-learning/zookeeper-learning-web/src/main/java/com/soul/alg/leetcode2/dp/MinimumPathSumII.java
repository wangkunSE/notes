package com.soul.alg.leetcode2.dp;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 * right which minimizes the sum of all numbers along its path.
 * <p>
 * Example
 * Example 1:
 * Input:  [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * <p>
 * Explanation:
 * Path is: 1 -> 3 -> 1 -> 1 -> 1
 * <p>
 * <p>
 * Example 2:
 * Input:  [[1,3,2]]
 * Output: 6
 * <p>
 * Explanation:
 * Path is: 1 -> 3 -> 2
 *
 * @author Administrator
 * @version 2020/10/31
 */
public class MinimumPathSumII {

    static class Solution {
        /**
         * @param grid: a list of lists of integers
         * @return: An integer, minimizes the sum of all numbers along its path
         */
        public int minPathSum(int[][] grid) {
            // write your code here

            if (null == grid || null == grid[0] || grid[0].length < 1) {
                return 0;
            }


            // f(m,n) = min{ f(m-1,n),f(m,n-1)} + grid[m][n]

            int[][] dp = new int[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = grid[i][j];
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    }

                }
            }

            return dp[grid.length - 1][grid[0].length - 1];

        }
    }

}
