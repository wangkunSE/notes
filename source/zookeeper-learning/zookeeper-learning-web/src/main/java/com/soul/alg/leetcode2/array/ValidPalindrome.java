package com.soul.alg.leetcode2.array;

import com.soul.alg.tencet.Soultion;

/**
 * @author wangkunwk
 * @version 2020/8/6
 */
public class ValidPalindrome extends AbstractAlg {

    private static class Solution {
        public boolean isPalindrome(String s) {
            if (null == s) {
                return false;
            }

            char[] chars = s.toCharArray();
            int i = 0;
            int j = chars.length - 1;
            while (i <= j) {
                char head = chars[i];
                char tail = chars[j];
                if (!isAlp(head)) {
                    i++;
                    continue;
                }

                if (!isAlp(tail)) {
                    j--;
                    continue;
                }

                if (Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                    return false;
                }
                i++;
                j--;

            }

            return true;
        }

        private boolean isAlp(char aChar) {
            return (aChar <= 'z' && aChar >= 'a') || (aChar <= 'Z' && aChar >= 'A') || (aChar <= '9' && aChar >= '0');
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome("race a car");
        System.out.println(palindrome);
    }
}
