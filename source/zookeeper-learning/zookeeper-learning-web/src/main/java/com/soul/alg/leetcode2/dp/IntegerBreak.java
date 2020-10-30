package com.soul.alg.leetcode2.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given a positive integer n, break it into the
 * sum of at least two positive integers and
 * maximize the product of those integers.
 * Return the maximum product you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 * @author wangkunwk
 * @version 2020/10/30
 */
public class IntegerBreak {

    static class Solution {
        public int integerBreak(int n) {
            if (n < 1) {
                return 0;
            }

            if (n == 4) {
                return 4;
            } else if (n == 3) {
                return 2;
            } else if (n == 2 || n == 1) {
                return 1;
            }

            int mod = n % 3;
            int div = n / 3;
            boolean needTwo = mod == 1;

            int product = 1;

            if (needTwo) {
                div--;
            }
            for (int i = 0; i < div; i++) {
                product *= 3;
            }

            if (needTwo) {
                product = product * 4;
            } else {
                if (mod != 0) {
                    product *= mod;
                }
            }

            return product;
        }
    }

    static class SolutionII {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;


            for (int i = 2; i < n + 1; i++) {
                int max = 0;
                for (int j = 1; j <= i / 2; j++) {
                    max = Math.max(max, dp[i - j] * j);
                }
                if (i == n) {
                    return max;
                }
                dp[i] = Math.max(max, i);
            }


            return dp[n];
        }
    }


    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        System.out.println(solution.integerBreak(6));
    }
}
