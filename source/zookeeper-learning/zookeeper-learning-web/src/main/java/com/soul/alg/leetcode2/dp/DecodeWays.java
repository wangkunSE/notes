package com.soul.alg.leetcode2.dp;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * we can't decode an empty string. So you should return 0 if the message is empty.
 * The length of message n \leq 100  n≤100
 * <p>
 * Have you met this question in a real interview?
 * Example
 * Example 1:
 * <p>
 * Input: "12" 123 abc lc aw 1234
 * Output: 2
 * Explanation: It could be decoded as AB (1 2) or L (12).
 * Example 2:
 * <p>
 * Input: "10"
 * Output: 1
 *
 * @author Administrator
 * @version 2020/10/31
 */
public class DecodeWays {

    static public class Solution {
        /**
         * @param s: a string,  encoded message
         * @return: an integer, the number of ways decoding
         */
        public int numDecodings(String s) {
            // write your code here

            if (null == s || s.length() < 1) {
                return 0;
            }

            int[] dp = new int[s.length() + 1];
            dp[0] = 1;


            for (int i = 1; i <= s.length(); i++) {
                dp[i] = 0;
                //f(n) = f(n-1) + f(n-2)
                int curNum = s.charAt(i - 1) - '0';
                if (1 <= curNum && curNum <= 9) {
                    dp[i] += dp[i - 1];
                }
                if (i >= 2) {
                    curNum = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                    if (10 <= curNum && curNum <= 26) {
                        dp[i] += dp[i - 2];
                    }
                }
            }

            return dp[s.length()];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.numDecodings("1234234"));
    }


}
