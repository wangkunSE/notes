package com.soul.alg.leetcode2.dp;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such
 * that no two adjacent houses have the same color, and you need to cost the least. Return the minimum cost.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
 * costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1
 * with color green, and so on... Find the minimum cost to paint all houses.
 * <p>
 * All costs are positive integers.
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: [[14,2,11],[11,14,5],[14,3,10]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 * Example 2:
 * <p>
 * Input: [[1,2,3],[1,4,6]]
 * Output: 3
 * Related Problems
 *
 * @author Administrator
 * @version 2020/10/31
 */
public class PaintHouses {

    static class Solution {
        /**
         * @param costs: n x 3 cost matrix
         * @return: An integer, the minimum cost to paint all houses
         */
        public int minCost(int[][] costs) {
            // write your code here


            //f(i,0) = min{f(i-1,1),f(i-1,2)}+cost[i,0]
            //f(i,1) = min{f(i-1,0),f(i-1,2)}+cost[i,1]
            //f(i,2) = min{f(i-1,0),f(i-1,1)}+cost[i,2]

            if (null == costs) {
                return 0;
            }

            int[][] dp = new int[costs.length + 1][costs[0].length];


            for (int i = 0; i < costs.length + 1; i++) {

                for (int j = 0; j < costs[0].length; j++) {
                    if (i == 0) {
                        dp[i][j] = 0;
                        continue;
                    }
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = 0; k < costs[0].length; k++) {
                        if (k == j) {
                            continue;
                        }
                        if (dp[i - 1][k] + costs[i - 1][j] < dp[i][j]) {
                            dp[i][j] = dp[i - 1][k] + costs[i - 1][j];
                        }
                    }
                }
            }

            int[] ints = dp[costs.length];
            return Math.min(ints[0], Math.min(ints[1], ints[2]));

        }
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{{1, 2, 3}, {1, 4, 6}};

        Solution solution = new Solution();
        System.out.println(solution.minCost(costs));
    }
}
