package com.soul.alg.leetcode2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2020/8/6
 */
public class ReverseVowelsString {
    private static class Solution {

        private static List<Character> vowels = new ArrayList<>();

        static {
            vowels.add('a');
            vowels.add('e');
            vowels.add('i');
            vowels.add('o');
            vowels.add('u');
        }

        public String reverseVowels(String s) {

            if (null == s){
                return s;
            }
            char[] chars = s.toCharArray();
            int i = 0;
            int j = chars.length-1;
            while (i<=j){
                char head = chars[i];
                char tail = chars[j];
                if (!isVowels(head)){
                    i++;
                    continue;
                }

                if (!isVowels(tail)){
                    j--;
                    continue;
                }

                mySwap(chars,i++,j--);

            }

            return new String(chars);
        }

        private boolean isVowels(char aChar) {
            return vowels.contains(Character.toLowerCase(aChar));
        }

        private void mySwap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String leetcode = solution.reverseVowels("leetcode");
        System.out.println(leetcode);
    }
}
