package com.soul.alg.leetcode2.dp;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * @author wangkunwk
 * @version 2020/10/19
 */
public class CoinCount extends AbstractAlg {

    static class Solution {


        /**
         * state f(n) = min{f(n-1) +1,f(n-3)+1,f(n-5)+1}
         * <p>
         * f(0) = 0
         * f(1) = 1
         * f(-n) = Integer.MAX_VALUE
         *
         * @param n
         * @return
         */

        public int getCoinCount(int n) {

            if (n <= 0) {
                return 0;
            }

            int[] minResult = initMinRes();

            int[] result = new int[n + 1];
            result[0] = 0;
            result[1] = 1;

            for (int i = 2; i < n + 1; i++) {
                int min = Integer.MAX_VALUE;

                if (i >= 5) {
                    minResult[2] = result[i - 5] + 1;
                }

                if (i >= 3) {
                    minResult[1] = result[i - 3] + 1;
                }

                minResult[0] = result[i - 1] + 1;
                min = Math.min(minResult[0], minResult[1]);
                result[i] = Math.min(min, minResult[2]);

                minResult = initMinRes();


            }


            return result[n];

        }

        private int[] initMinRes() {
            int[] minResult;
            minResult = new int[3];
            minResult[0] = Integer.MAX_VALUE;
            minResult[1] = Integer.MAX_VALUE;
            minResult[2] = Integer.MAX_VALUE;
            return minResult;
        }


    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 12; i++) {
            System.out.println("i: " + i + " solution: " + solution.getCoinCount(i));
        }
    }
}
