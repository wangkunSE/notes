package com.soul.alg.leetcode2.dp;

import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/10/21
 */
public class CoinChange {
    static class Solution {
        /**
         * @param coins:  a list of integer
         * @param amount: a total amount of money amount
         * @return: the fewest number of coins that you need to make up
         */
        public int coinChange(int[] coins, int amount) {
            // write your code here
            if (amount < 1) {
                return 0;
            }

            if (Objects.isNull(coins)) {
                return -1;
            }

            int[] dp = new int[amount + 1];
            dp[0] = 0;

            for (int i = 1; i < amount + 1; i++) {
                int res = Integer.MAX_VALUE - 1;
                for (int coin : coins) {
                    if (coin < 1) {
                        continue;
                    }
                    if (i >= coin) {
                        res = Math.min(res, dp[i - coin] + 1);
                    }
                }
                dp[i] = res;
            }
            return dp[amount] == Integer.MAX_VALUE - 1 ?-1:dp[amount];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = new int[]{0, 1, 1, 1, 8};
        System.out.println(solution.coinChange(coins, 9));
    }

}
