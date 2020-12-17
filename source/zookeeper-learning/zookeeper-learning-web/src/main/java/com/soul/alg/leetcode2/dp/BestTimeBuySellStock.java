package com.soul.alg.leetcode2.dp;

/**
 * Say you have an array for which the ith element
 * is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at
 * most one transaction (ie, buy one and sell one share
 * of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Example
 * Example 1
 * <p>
 * Input: [3, 2, 3, 1, 2]
 * Output: 1
 * Explanation: You can buy at the third day and
 * then sell it at the 4th day. The profit is 2 - 1 = 1
 * Example 2
 * <p>
 * Input: [1, 2, 3, 4, 5]
 * Output: 4
 * Explanation: You can buy at the 0th day and
 * then sell it at the 4th day. The profit is 5 - 1 = 4
 * Example 3
 * <p>
 * Input: [5, 4, 3, 2, 1]
 * Output: 0
 * Explanation: You can do nothing and get nothing.
 *
 * @author wangkunwk
 * @version 2020/11/26
 */
public class BestTimeBuySellStock {

    public class Solution {
        /**
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int[] prices) {
            // write your code here

            int res = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < min) {
                    min = prices[i];
                } else {
                    res = Math.max(res, prices[i] - min);
                }
            }
            return res;
        }
    }

}
