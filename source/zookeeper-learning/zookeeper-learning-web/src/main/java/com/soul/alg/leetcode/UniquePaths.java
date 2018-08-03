package com.soul.alg.leetcode;

import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/2
 * @time: 下午12:00
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class UniquePaths {
    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();
        Third third = new Third();
        Fourth fourth = new Fourth();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int minPathSum = third.minPathSum(grid);
        int pathSum = fourth.minPathSum(grid);
        System.out.println(pathSum);
//        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
//        int pathsWithObstacles = second.uniquePathsWithObstacles(obstacleGrid);
//        int paths = first.uniquePaths(10, 10);
//        System.out.println(paths);
//        System.out.println(pathsWithObstacles);
        boolean numeric = StringUtils.isNumeric("2e10");
        System.out.println(numeric);
    }

    private static class First {
        public int uniquePaths(int m, int n) {
            if (m <= 0 || n <= 0) {
                return 0;
            }
            int N = m + n - 2;
            int k = m > n ? n - 1 : m - 1;
            long molecular = 1;
            long denominator = 1;
            int count = 1;
            for (int i = 1; i <= k; i++) {
                molecular *= (N - k + i);
                denominator *= i;
            }

            return (int) (molecular / denominator);
        }

    }

    private static class Second {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            if (obstacleGrid == null) {
                return 0;
            }

            int row = obstacleGrid.length;
            int column = obstacleGrid[0].length;
            int[][] dp = new int[row][column];

            for (int i = 0, j = 0; j < column; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = 1;
                }
            }

            for (int i = 0, j = 0; i < row; i++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = 1;
                }
            }

            for (int i = 1; i < row; i++) {
                for (int j = 1; j < column; j++) {
                    if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] != 1) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (obstacleGrid[i][j - 1] == 1 && obstacleGrid[i - 1][j] != 1) {
                        dp[i][j] = dp[i - 1][j];
                    } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }

            return dp[row - 1][column - 1];
        }
    }

    private static class Third {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            int[][] sum = new int[m][n];

            sum[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                sum[i][0] = sum[i - 1][0] + grid[i][0];
            }

            for (int j = 1; j < n; j++) {
                sum[0][j] = sum[0][j - 1] + grid[0][j];
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    sum[i][j] = Math.min(sum[i - 1][j] + grid[i][j], sum[i][j - 1] + grid[i][j]);
                }
            }

            return sum[m - 1][n - 1];
        }
    }

    private static class Fourth {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;

            int[] cur = new int[n];
            cur[0] = grid[0][0];
            for (int i = 1; i < n; i++) {
                cur[i] = cur[i - 1] + grid[0][i];
            }

            for (int i = 1; i < m; i++) {
                cur[0] += grid[i][0];
                for (int j = 1; j < n; j++) {
                    cur[j] = Math.min(cur[j], cur[j - 1]) + grid[i][j];
                }
            }
            return cur[n - 1];
        }
    }
}
