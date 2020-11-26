package com.soul.alg.leetcode2.dp;

import java.util.Objects;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some
 * characters(can be none) deleted without changing the relative order of the remaining
 * characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of
 * two strings is a subsequence that is common to both strings.
 * <p>
 * <p>
 * <p>
 * If there is no common subsequence, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class LongestCommonSubsequence {


    static class Solution {
        public int longestCommonSubsequence(String text1, String text2) {


            //f(x,y) = 0 if i==0 j==0
            //f(x,y) = f(x-1,y-1) + 1 if x^m == y^n
            //f(x,y) = max(f(x-1,y) , f(x,y-1)) if x^m != y^n


            if (Objects.isNull(text1) || text1.length() < 1
                    || Objects.isNull(text2) || text2.length() < 1) {
                return 0;
            }
            char[] X = text1.toCharArray();
            char[] Y = text2.toCharArray();
            int[][] dp = new int[X.length + 1][Y.length + 1];

            for (int i = 0; i < X.length + 1; i++) {
                for (int j = 0; j < Y.length + 1; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                        continue;
                    }

                    char x = X[i - 1];
                    char y = Y[j - 1];
                    if (x == y) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                }
            }

            return dp[X.length][Y.length];

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace"));
    }

}
