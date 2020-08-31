package com.soul.alg.leetcode2.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkunwk
 * @version 2020/8/18
 */
public class LongestSubstringWithoutRepeating {

    static class Solution {
        public int lengthOfLongestSubstring(String s) {

            if (s == null || s.length() < 1) {
                return 0;
            }
            Map<Character, Integer> index = new HashMap<>();
            int maxLength = 0;
            int tempLength = 0;
            for (int i = 0; i < s.length(); i++) {
                char currentChar = s.charAt(i);
                if (null != index.get(currentChar)) {
                    maxLength = Math.max(maxLength, tempLength);
                    tempLength = 0;
                    i = index.get(currentChar);
                    index.clear();
                } else {
                    tempLength++;
                    index.put(currentChar, i);
                }
            }
            maxLength = Math.max(maxLength, tempLength);

            return maxLength;
        }
    }

    class SolutionII{

        public int lengthOfLongestSubstring(String s) {
            int len = 0;
            if (s == null || s.length() == 0) return len;

            char[] inputArray = s.toCharArray();
            int left = 0, right = 0;
            boolean[] occurrence = new boolean[128];
            while (right < inputArray.length) {
                if (occurrence[inputArray[right]] == false) {
                    occurrence[inputArray[right]] = true;
                    right++;
                } else {
                    len = Math.max(len, right - left);
                    while (left < right && inputArray[left] != inputArray[right]) {
                        occurrence[inputArray[left]] = false;
                        left++;
                    }
                    right++;
                    left++;
                }
            }
            len = Math.max(len, right - left);
            return len;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.lengthOfLongestSubstring(" ");
        System.out.println(result);
    }
}
