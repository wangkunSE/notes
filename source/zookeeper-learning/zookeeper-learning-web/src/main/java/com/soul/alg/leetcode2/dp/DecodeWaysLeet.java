package com.soul.alg.leetcode2.dp;

import java.util.Objects;

/**
 * A message containing letters from A-Z is being encoded to numbers using
 * the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number
 * of ways to decode it.
 * <p>
 * The answer is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * <p>
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with '0'.
 * We cannot ignore a zero when we face
 * it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
 *
 * @author wangkunwk
 * @version 2020/11/2
 */
public class DecodeWaysLeet {

    static class Solution {
        public int numDecodings(String s) {

            //f(x) = f(x-1) | c in 1~9  + f(x-2)|c in 10~26

            if (Objects.isNull(s)) {
                return 0;
            }

            if (s.length() < 1) {
                return 1;
            }

            char[] chars = s.toCharArray();
            int[] dp = new int[chars.length + 1];
            dp[0] = 1;

            for (int i = 1; i < chars.length+1; i++) {
                dp[i] = 0;
                int cur = chars[i - 1] - '0';
                if (cur >= 1 && cur <= 9) {
                    dp[i] += dp[i - 1];
                }

                if (i >= 2) {
                    cur = (chars[i - 2] - '0') * 10 + cur;

                    if (cur >= 10 && cur <= 26) {
                        dp[i] += dp[i - 2];
                    }
                }

            }

            return dp[chars.length];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("12"));
    }

}
