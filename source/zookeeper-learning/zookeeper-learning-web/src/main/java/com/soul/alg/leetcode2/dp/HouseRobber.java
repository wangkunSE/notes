package com.soul.alg.leetcode2.dp;

/**
 * You are a professional robber planning to rob
 * houses along a street. Each house has a certain
 * amount of money stashed, the only constraint
 * stopping you from robbing each of them is that
 * adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the
 * amount of money of each house, determine the maximum
 * amount of money you can rob tonight without alerting the police.
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: [3, 8, 4]
 * Output: 8
 * Explanation: Just rob the second house.
 * Example 2:
 * <p>
 * Input: [5, 2, 1, 3]
 * Output: 8
 * Explanation: Rob the first and the last house.
 * Challenge
 * O(n) time and O(1) memory.
 *
 * @author wangkunwk
 * @version 2020/11/26
 */
public class HouseRobber {

    static class Solution {
        /**
         * @param A: An array of non-negative integers
         * @return: The maximum amount of money you can rob tonight
         */
        public long houseRobber(int[] A) {

            //f(i) = max{f[i-1],f[i-2]+A[i-1]}

            // write your code here

            // res = Math.max (notRob+A[i], rob);

            int notRob, rob;
            notRob = rob = 0;

            int curNotRob;

            for (int i = 0; i < A.length; i++) {
                curNotRob = notRob;
                notRob = Math.max(rob, notRob);
                rob = Math.max(curNotRob + A[i], rob);
            }

            return Math.max(rob, notRob);

        }
    }


    static class SolutionII {
        /**
         * @param A: An array of non-negative integers
         * @return: The maximum amount of money you can rob tonight
         */
        public long houseRobber(int[] A) {

            //f(i) = max{f[i-1],f[i-2]+A[i-1]}

            // write your code here

            // res = Math.max (notRob+A[i], rob);

            if (A == null || A.length < 1) {
                return 0;
            }

            int[] dp = new int[A.length + 1];
            dp[0] = 0;
            dp[1] = A[0];
            for (int i = 2; i < A.length; i++) {
                dp[i] = Math.max(dp[i - 2] + A[i - 1], dp[i - 1]);
            }

            return dp[A.length];

        }
    }


}
