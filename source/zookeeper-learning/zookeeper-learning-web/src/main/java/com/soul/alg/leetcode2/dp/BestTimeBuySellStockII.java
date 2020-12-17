package com.soul.alg.leetcode2.dp;

/**
 * Given an array prices, which represents
 * the price of a stock in each day.
 * <p>
 * You may complete as many transactions
 * as you like (ie, buy one and sell one share
 * of the stock multiple times).
 * However, you may not engage in multiple transactions
 * at the same time (ie, if you already have the stock,
 * you must sell it before you buy again).
 * <p>
 * Design an algorithm to find the maximum profit.
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: [2, 1, 2, 0, 1]
 * Output: 2
 * Explanation:
 * 1. Buy the stock on the second day at 1,
 * and sell the stock on the third day at 2. Profit is 1.
 * 2. Buy the stock on the 4th day at 0,
 * and sell the stock on the 5th day at 1. Profit is 1.
 * Total profit is 2.
 * Example 2:
 * <p>
 * Input: [4, 3, 2, 1]
 * Output: 0
 * Explanation: No transaction, profit is 0.
 *
 * @author wangkunwk
 * @version 2020/11/26
 */
public class BestTimeBuySellStockII {


    static class Solution {
        /**
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int[] prices) {
            // write your code here

            if (prices == null || prices.length < 1) {
                return 0;
            }

            int res = 0;
            int start = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < prices[i-1]) {
                    res += (prices[i - 1] - prices[start]);
                    start = i;
                }
            }

            if (start < prices.length) {
                res += (prices[prices.length - 1] - prices[start]);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stock = new int[]{3, 2, 6, 5, 0, 3};
        int i = solution.maxProfit(stock);
        System.out.println(i);
    }

}
