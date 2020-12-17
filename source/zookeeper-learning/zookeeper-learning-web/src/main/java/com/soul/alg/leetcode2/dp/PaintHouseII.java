package com.soul.alg.leetcode2.dp;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * There are a row of n houses, each house can be
 * painted with one of the k colors. The cost of
 * painting each house with a certain color is different.
 * You have to paint all the houses such that no two
 * adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain
 * color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house
 * 0 with color 0; costs[1][2] is the cost of painting
 * house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 * <p>
 * Example
 * Example 1
 * <p>
 * Input:
 * costs = [[14,2,11],[11,14,5],[14,3,10]]
 * Output: 10
 * Explanation:
 * The three house use color [1,2,1] for each house.
 * The total cost is 10.
 * Example 2
 * <p>
 * Input:
 * costs = [[5]]
 * Output: 5
 * Explanation:
 * There is only one color and one house.
 * Challenge
 * Could you solve it in O(nk)
 *
 * @author wangkunwk
 * @version 2020/11/26
 */
public class PaintHouseII extends AbstractAlg {

    static class Solution {
        /**
         * @param A: n x k cost matrix
         * @return: an integer, the minimum cost to paint all houses
         */
        public int minCostII(int[][] A) {
            // write your code here
            //f(i,j) = min(f(i-1,k))+A[i-1][j]

            if (A == null || A.length == 0) {
                return 0;
            }

            int n = A.length;
            int K = A[0].length;
            int[][] dp = new int[n + 1][K];

            int min1, min2;
            int j1 = 0, j2 = 0;

            for (int i = 0; i < K; i++) {
                dp[0][i] = 0;
            }


            for (int i = 1; i < n + 1; i++) {
                min1 = min2 = Integer.MAX_VALUE;
                for (int j = 0; j < K; j++) {
                    if (dp[i - 1][j] < min1) {
                        min2 = min1;
                        j2 = j1;
                        min1 = dp[i - 1][j];
                        j1 = j;
                    } else if (dp[i - 1][j] < min2) {
                        min2 = dp[i - 1][j];
                        j2 = j;
                    }
                }

                for (int j = 0; j < K; j++) {
                    if (j != j1) {
                        dp[i][j] = dp[i - 1][j1] + A[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j2] + A[i - 1][j];
                    }
                }
            }


            int res = Integer.MAX_VALUE;
            for (int i = 0; i < K; i++) {
                res = Math.min(res, dp[n][i]);
            }
            return res;
        }
    }
}
