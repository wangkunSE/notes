package com.soul.alg.leetcode2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2020/8/18
 */
public class AllAnagramsInString {

    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {

            List<Integer> result = new ArrayList<>();
            if (s == null || p == null || s.length() < 1 || p.length() < 1 || s.length() < p.length()) {
                return result;
            }

            int[] index = new int[128];
            char[] chars = p.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index[chars[i]]++;
            }
            int[] temp = new int[128];
            char[] sArray = s.toCharArray();
            int left = 0;
            int right = 0;
            while (right < sArray.length) {
                char rightChar = sArray[right];
                temp[rightChar]++;
                if (index[rightChar] == 0) {
                    while (left < right) {
                        int leftChar = sArray[left++];
                        if (temp[leftChar] > 0) {
                            temp[leftChar]--;
                        }
                    }
                    left++;
                } else if (temp[rightChar] > index[rightChar]) {
                    while (left < right && temp[rightChar] > index[rightChar]) {
                        int leftChar = sArray[left++];
                        temp[leftChar]--;
                    }
                } else {
                    if ((right - left + 1) == p.length()) {
                        temp[sArray[left]]--;
                        result.add(left++);
                    }
                }
                right++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> anagrams = solution.findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}
