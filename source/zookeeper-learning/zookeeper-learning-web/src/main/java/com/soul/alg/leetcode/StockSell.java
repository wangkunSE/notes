package com.soul.alg.leetcode;

import java.util.Arrays;

/**
 * @author wangkun1
 * @version 2018/4/23
 */
public class StockSell {

    public static void main(String[] args) {
//        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
//        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {1, 3, 2, 8, 4, 9};
        int maxProfit = new TransactionWithFee().maxProfit(prices,2);
//        int maxProfit = new Third().maxProfit(prices);
        System.out.println(maxProfit);
    }

    private static class Third {
        public int maxProfit(int[] prices) {
            int min = prices[0];
            int length = prices.length;
            int[] maxBefore = new int[length];
            for (int i = 1; i < length; i++) {
                maxBefore[i] = Math.max(maxBefore[i - 1], prices[i] - min);
                min = Math.min(min, prices[i]);
            }
            int result = 0;
            int max = prices[length - 1];
            for (int i = length - 2; i >= 0; i--) {
                max = Math.max(prices[i], max);
                result = Math.max(result, max - prices[i] + maxBefore[i]);
            }
            return result;
        }
    }

    private static class Discussion {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2)
                return 0;
            int[] maxBefore = new int[len];
            int min = prices[0];
            for (int i = 1; i < len; i++) {
                maxBefore[i] = Math.max(maxBefore[i - 1], prices[i] - min);
                min = Math.min(min, prices[i]);
            }
            int max = prices[len - 1];
            int ret = 0;
            for (int i = len - 2; i >= 0; i--) {
                max = Math.max(prices[i], max);
                ret = Math.max(ret, max - prices[i] + maxBefore[i]);
            }
            return ret;
        }
    }

    private static class TwoTraverses {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int length = prices.length;

            int[] leftProfit = new int[length];
            int leftMaxProfit = 0;
            int leftMin = prices[0];
            for (int i = 0; i < length; i++) {
                if (prices[i] < leftMin) leftMin = prices[i];
                if (prices[i] - leftMin > leftMaxProfit) leftMaxProfit = prices[i] - leftMin;
                leftProfit[i] = leftMaxProfit;
            }

            int maxProfit = 0;
            int rightMaxProfit = 0;
            int rightMax = prices[length - 1];
            for (int i = length - 1; i >= 0; i--) {
                if (prices[i] > rightMax) rightMax = prices[i];
                if (rightMax - prices[i] > rightMaxProfit) rightMaxProfit = rightMax - prices[i];
                int currentProfit = rightMaxProfit + (i > 0 ? leftProfit[i - 1] : 0);
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }

            return maxProfit;
        }
    }

    private static class KTransaction {
        public int maxProfit(int k, int[] prices) {
            int len = prices.length;
            if (k >= len / 2) return quickSolve(prices);

            int[][] t = new int[k + 1][len];
//            for (int i = 1; i <= k; i++) {
//                int tmpMax =  -prices[0];
//                for (int j = 1; j < len; j++) {
//                    t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
//                    tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
//                }
//            }
            for (int i = 1; i < k + 1; i++) {
                int tmpMax = -prices[0];
                for (int j = 1; j < len; j++) {
                    t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                    tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
                }
            }
            return t[k][len - 1];
        }


        private int quickSolve(int[] prices) {
            int len = prices.length, profit = 0;
            for (int i = 1; i < len; i++)
                // as long as there is a price gap, we gain a profit.
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
    }

    private static class TransactionWithCooldown {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 0) {
                return 0;
            }
            int b0 = -prices[0], b1 = b0;
            int s0 = 0, s1 = 0, s2 = 0;
            for (int i = 1; i < prices.length; i++) {
                b0 = Math.max(b1, s2 - prices[i]);
                s0 = Math.max(s1, b1 + prices[i]);
                b1 = b0;
                s2 = s1;
                s1 = s0;
            }
            return s0;
        }
    }

    private static class TransactionWithFee {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length <= 0) {
                return 0;
            }
            int b0 = -prices[0], b1 = b0;
            int s0 = 0, s1 = 0;
            for (int i = 1; i < prices.length; i++) {
                b0 = Math.max(b1, s1 - prices[i]);
                s0 = Math.max(s1, b1 + prices[i] - 2);
                b1 = b0;
                s1 = s0;
            }
            return s0;
        }
    }
}
