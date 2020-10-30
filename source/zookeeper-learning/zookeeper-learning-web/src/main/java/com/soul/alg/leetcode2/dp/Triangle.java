package com.soul.alg.leetcode2.dp;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * <p>
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 *
 * @author wangkunwk
 * @version 2020/10/27
 */
public class Triangle {

    static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if (Objects.isNull(triangle)) {
                return 0;
            }

            int[] dp = new int[triangle.size() + 1];
            for (int i = 0; i < triangle.size(); i++) {
                List<Integer> list = triangle.get(i);
                if (i == 0) {
                    dp[0] = list.get(0);
                    continue;
                }
                for (int j = list.size() - 1; j >= 0; j--) {
                    if (j == 0) {
                        dp[j] = list.get(j) + dp[j];
                    } else if (j == list.size() - 1) {
                        dp[j] = list.get(j) + dp[j - 1];
                    } else {
                        dp[j] = list.get(j) + Math.min(dp[j], dp[j - 1]);
                    }
                }
            }
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < triangle.size(); i++) {
                min = Math.min(min, dp[i]);
            }

            return min;

        }
    }

    static class SolutionII {
        class Solution {
            public int minimumTotal(List<List<Integer>> triangle) {
                int n = triangle.size();
                int[] f = new int[n];
                f[0] = triangle.get(0).get(0);

                dp(triangle, f, 1);

                // for (int i = 1; i < n; i++) {
                //     f[i] = f[i - 1] + triangle.get(i).get(i);
                //     for (int j = i - 1; j > 0; j--) {
                //         f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
                //     }
                //     f[0] += triangle.get(i).get(0);
                // }

                int minTotal = f[0];
                for (int i = 0; i < n; i++) {
                    minTotal = Math.min(minTotal, f[i]);
                }
                return minTotal;
            }

            public void dp(List<List<Integer>> triangle, int[] f, int i) {
                if (i >= triangle.size())
                    return;
                f[i] = f[i - 1] + triangle.get(i).get(i);
                for (int j = i - 1; j > 0; --j) {
                    f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
                }
                f[0] += triangle.get(i).get(0);

                dp(triangle, f, i + 1);
            }
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> triangle = Lists.newArrayList();
        triangle.add(Lists.newArrayList(-1));
        triangle.add(Lists.newArrayList(-2, -3));
        System.out.println(solution.minimumTotal(triangle));
    }


}
