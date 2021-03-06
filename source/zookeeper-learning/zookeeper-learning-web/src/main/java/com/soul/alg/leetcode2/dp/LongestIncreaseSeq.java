package com.soul.alg.leetcode2.dp;

import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/10/26
 */
public class LongestIncreaseSeq {

    static class Solution {


        public int getLongestIncreaseSeq(int[] arr) {

            if (Objects.isNull(arr) || arr.length < 1) {
                return 0;
            }

            int[] dp = new int[arr.length];
            dp[0] = 1;
            int maxLength = 0;

            for (int i = 0; i < arr.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[j] <= arr[i]) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                    }
                }
                maxLength = Math.max(maxLength, dp[i]);
            }

            return maxLength;
        }

    }

    static class SolutionII {
        public int getLongestIncreaseSeq(int[] arr) {

            if (Objects.isNull(arr) || arr.length < 1) {
                return 0;
            }

            int[] dp = new int[arr.length];

            int max = 0;

            for (int i = 0; i < arr.length; i++) {
                dp[i] = 1;

                if (i > 0 && arr[i] > arr[i - 1]) {
                    dp[i] = dp[i - 1] + 1;
                }

                max = Math.max(dp[i], max);

            }
            return max;
        }


    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();

        int[] arr = new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7};

        System.out.println(solution.getLongestIncreaseSeq(arr));
    }

}
