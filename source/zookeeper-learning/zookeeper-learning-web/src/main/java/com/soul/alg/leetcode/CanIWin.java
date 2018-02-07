package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkun1
 * @version 2018/2/7
 */
public class CanIWin {
    static class Solution {

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

            if (validateParam(maxChoosableInteger, desiredTotal)) {
                if (maxChoosableInteger >= desiredTotal) {
                    return true;
                }
                Integer targetNum = desiredTotal - maxChoosableInteger - 1;
                return canIGetTargetNum(maxChoosableInteger, targetNum, desiredTotal);
            }
            return false;
        }

        private boolean canIGetTargetNum(int maxChoosableInteger, Integer targetNum, int desiredTotal) {
            List<Integer> choosableInteger = new ArrayList<>();
            for (int i = 1; i <= maxChoosableInteger; i++) {
                choosableInteger.add(i);
            }
            Integer sum = 0;
            Integer count = 0;
            while (sum < targetNum) {
                if (reachTarget(choosableInteger, targetNum, sum)) {
                    return count % 2 == 0;
                }
                Integer first = choosableInteger.get(0);
                boolean flag = true;
                if (choosableInteger.size() % 2 != 0) {
                    if (targetNum % 2 == 0 && targetNum / 2 == choosableInteger.get(choosableInteger.size() / 2)) {
                        first = choosableInteger.get(choosableInteger.size() / 2);
                        flag = false;
                    }
                }
                sum += first;
//                desiredTotal = desiredTotal - first;
                choosableInteger.remove(first);
                count++;
                if (flag) {
                    targetNum = desiredTotal - choosableInteger.get(choosableInteger.size() - 1) - choosableInteger.get(0);
                }
            }

            return false;
        }

        private boolean reachTarget(List<Integer> choosableInteger, Integer targetNum, Integer sum) {
            for (Integer integer : choosableInteger) {
                if (integer + sum == targetNum) {
                    return true;
                }
            }
            return false;
        }

        private boolean validateParam(int maxChoosableInteger, int desiredTotal) {
            if (maxChoosableInteger <= 0) {
                return false;
            }
            if (desiredTotal <= 0) {
                return true;
            }
            Integer sum = 0;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                sum += i;
            }
            return sum >= desiredTotal;
        }
    }

    public static void main(String[] args) {
//        testCases();
        int n = 0;
        System.out.println(n << 1);
    }

    private static void testCases() {
        Solution solution = new Solution();
        boolean b = solution.canIWin(11, 25);
        System.out.println(b);
    }
}
