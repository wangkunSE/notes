package com.soul.alg.leetcode2.dp;


/**
 * @author Administrator
 * @version 2020/10/31
 */
public class UniquePathII {


    static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            if (null == obstacleGrid || obstacleGrid[0] == null) {
                return 0;
            }

            int row = obstacleGrid.length;
            int column = obstacleGrid[0].length;
            if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][column - 1] == 1) {
                return 0;
            }

            int[][] dp = new int[row][column];
            dp[0][0] = 1;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {

                    if (i == 0 && j == 0) {
                        continue;
                    }

                    if (i == 0) {
                        dp[i][j] = obstacleGrid[i][j - 1] == 1 ? -1 : dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = obstacleGrid[i - 1][j] == 1 ? -1 : dp[i - 1][j];
                    } else {
                        int up = obstacleGrid[i - 1][j] == 1 ? 0 : dp[i - 1][j];
                        int left = obstacleGrid[i][j - 1] == 1 ? 0 : dp[i][j - 1];
                        dp[i][j] = up + left;

                    }


                }
            }

            return dp[row - 1][column - 1];

        }
    }
}
