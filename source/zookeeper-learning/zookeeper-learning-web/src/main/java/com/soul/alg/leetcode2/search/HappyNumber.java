package com.soul.alg.leetcode2.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class HappyNumber {

    static class Solution {
        public boolean isHappy(int n) {

            if (n < 1) {
                return false;
            }

            int tmpNumber = 0;
            int sum = n;

            while (true) {

                while (sum >= 1) {
                    int tmpValue = sum % 10;
                    tmpNumber += tmpValue * tmpValue;
                    sum /= 10;
                }

                if (tmpNumber == 1) {
                    return true;
                }
                if (tmpNumber == 4) {
                    return false;
                }

                System.out.println(tmpNumber);

                sum = tmpNumber;
                tmpNumber = 0;
            }

        }
    }

    static class SolutionII {
        public boolean isHappy(int n) {

            if (n == 1) {
                return true;
            }


            int tmpNumber = 0;
            int sum = n;

            while (sum >= 1) {
                int tmpValue = sum % 10;
                tmpNumber += tmpValue * tmpValue;
                sum /= 10;
            }

            if (tmpNumber == 1) {
                return true;
            }
            if (tmpNumber == 4) {
                return false;
            }


            return isHappy(tmpNumber);

        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        boolean happy = solution.isHappy(19);
        System.out.println(happy);
    }
}
