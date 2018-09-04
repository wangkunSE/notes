package com.soul.alg.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/21
 * @time: 下午1:11
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class NumDecoding {
    public static void main(String[] args) {
        Third third = new Third();
        int i = third.numDecodings("226");
        System.out.println(i);
    }

    private static class First {
        private Map<Integer, Integer> history = new HashMap<>();

        public int numDecodings(String s) {
            return decodeDigits(s, 0, 1) + decodeDigits(s, 0, 2);
        }

        private int decodeDigits(String s, int start, int end) {
            if (history.containsKey(start)) {
                return history.get(start);
            }
            if (start < s.length()) {
                int tempEnd = end;
                String str = null;
                try {
                    str = s.substring(start, end);
                } catch (StringIndexOutOfBoundsException e) {
                    str = null;
                }
                if (str == null || str.length() < 1) {
                    return 0;
                }
                int temp = Integer.parseInt(str);
                if (str.length() == 2 && temp < 10) {
                    return 0;
                }
                if (temp > 26 || temp < 1) {
                    return 0;
                }
                if (end == s.length()) {
                    return 1;
                }
                int result = decodeDigits(s, end, end + 1) + decodeDigits(s, end, end + 2);
                history.put(start, result);
                return result;
            }
            return 0;
        }
    }

    private static class Second {
        public int numDecodings(String s) {
            if (s == null) {
                return 0;
            }
            int[] memo = new int[s.length() + 1];
            Arrays.fill(memo, -1);
            memo[s.length()] = 1;
            return decodeString(s, 0, memo);
        }

        private int decodeString(String s, int start, int[] memo) {
            if (memo[start] > -1) {
                return memo[start];
            }
            if (s.charAt(start) == '0') {
                memo[start] = 0;
                return 0;
            }
            int result = decodeString(s, start + 1, memo);
            if (start + 1 < s.length() && (s.charAt(start) == '1' || (s.charAt(start) == '2' && s.charAt(start + 1) < '7'))) {
                result += decodeString(s, start + 2, memo);
            }
            memo[start] = result;
            return result;
        }
    }

    private static class Third {
        public int numDecodings(String s) {
            if (s == null || s.length() < 1) {
                return 0;
            }
            int current = 0, post = 1, postNext = 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    current = 0;
                } else {
                    int result = post;
                    if (i < s.length() - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                        result += postNext;
                    }
                    current = result;
                }
                postNext = post;
                post = current;
            }
            return current;
        }
    }
}
