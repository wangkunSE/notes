package com.soul.alg.leetcode2.array;

import com.soul.alg.leetcode2.AbstractAlg;

/**
 * @author wangkunwk
 * @version 2020/8/6
 */
public class ReverseString extends AbstractAlg {
    private static class Solution {
        public void reverseString(char[] s) {
            if (null == s || s.length<1){
                return;
            }

            int i = 0;
            int j = s.length-1;

            while (i<=j){
                mySwap(s,i++,j--);
            }
        }

        private void mySwap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        char[] chars = new char[]{'h','e','l','l'};
        solution.reverseString(chars);
        printArr(chars);
    }
}
