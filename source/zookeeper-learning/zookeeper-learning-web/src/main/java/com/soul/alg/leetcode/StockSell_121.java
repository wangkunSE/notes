package com.soul.alg.leetcode;

/**
 * @author wangkunwk
 * @version 2019/10/11
 */
public class StockSell_121 {

    public static void main(String[] args) {
        System.out.println(new StockSell_121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }
}
