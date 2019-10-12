package com.soul.alg.leetcode;

import java.util.TreeMap;

/**
 * @author wangkunwk
 * @version 2019/10/11
 */
public class StockSell_II_122 {
    public static void main(String[] args) {
        System.out.println(new StockSell_II_122().maxProfit(new int[]{7,1,5,3,6,4}));
    }

    public int maxProfit(int[] prices) {

        if (null == prices || prices.length <= 0) {
            return 0;
        }

        TreeMap treeMap = new TreeMap();

        int maxProfit = 0;
        int prePrice = prices[0];
        int curMinPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prePrice) {
                maxProfit = Math.max(maxProfit + (prePrice - curMinPrice), maxProfit);
                curMinPrice = prices[i];
            }
            if (i == prices.length - 1) {
                prePrice = prices[i];
                maxProfit = Math.max(maxProfit + (prePrice - curMinPrice), maxProfit);
            }
            prePrice = prices[i];
        }
        return maxProfit;
    }
}
