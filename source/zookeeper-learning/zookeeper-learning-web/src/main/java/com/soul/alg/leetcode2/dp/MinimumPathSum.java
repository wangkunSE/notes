package com.soul.alg.leetcode2.dp;

import java.util.Objects;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left
 * to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 * @author wangkunwk
 * @version 2020/10/29
 */
public class MinimumPathSum {

    static class Solution {
        public int minPathSum(int[][] grid) {

            if (Objects.isNull(grid)) {
                return 0;
            }


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
