package com.soul.alg.leetcode2.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/8/19
 */
public class MinimumWindowSubstring {
    static class Solution {
        public String minWindow(String s, String t) {

            if (null == s || null == t || s.length() < 1
                    || t.length() < 1 || s.length() < t.length()) {
                return "";
            }

            if (s.equals(t)){
                return s;
            }
            
            if (t.length()==1){
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == t.charAt(0)){
                        return t;
                    }
                }
                return "";
            }

            boolean[] dict = new boolean[128];
            for (int i = 0; i < t.length(); i++) {
                dict[t.charAt(i)] = true;
            }

            Map<Character, Integer> indexMap = new HashMap<>();
            int left = 0;
            int right = 0;
            int minLength = Integer.MAX_VALUE;
            String result = null;
            while (left < s.length() && !dict[s.charAt(left)]) {
                left++;
                right++;
            }
            right++;
            if (left >= s.length()) {
                return "";
            }
            indexMap.put(s.charAt(left), left);
            while (right < s.length()) {
                char rightChar = s.charAt(right);
                if (dict[rightChar]) {

                    Integer index = indexMap.get(rightChar);
                    if (Objects.nonNull(index)) {
                        indexMap.remove(s.charAt(left));
                        left++;
                        while (!dict[s.charAt(left)]) {
                            left++;
                        }
                    }
                    indexMap.put(rightChar, right);
                    if (indexMap.size() == t.length()) {
                        if (minLength > (right - left + 1)) {
                            minLength = right - left + 1;
                            result = s.substring(left, right + 1);
                        }
                        indexMap.remove(s.charAt(left));
                        left++;
                        while (!dict[s.charAt(left)]) {
                            left++;
                        }
                    }
                }
                right++;
            }
            return result;
        }
    }

    static class SolutionII{
        public String minWindow(String s, String t) {
            if(s == null || t == null) {
                return "";
            }
            char[] c1 = s.toCharArray();
            char[] c2 = t.toCharArray();
            int match = c2.length;
            int[] matchMap = new int[256];
            for(char c : t.toCharArray()) {
                matchMap[c]++;
            }
            int len = s.length();
            int left = 0;
            int right = 0;
            int minLen = Integer.MAX_VALUE;
            String result = "";
            while(right < len) {
                matchMap[c1[right]]--;
                if(matchMap[c1[right]] >= 0) {
                    match--;
                }
                if(match == 0) {
                    while(matchMap[c1[left]] < 0) {
                        matchMap[c1[left++]]++;
                    }
                    if(right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        result = s.substring(left, right + 1);
                    }
                    matchMap[c1[left++]]++;
                    match++;
                }
                right++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        String minWindow = solution.minWindow("bbaa", "aba");
        System.out.println(minWindow);
    }
}
