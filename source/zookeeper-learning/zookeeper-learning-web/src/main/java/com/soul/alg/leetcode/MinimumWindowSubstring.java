package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/19
 * @time: 上午10:08
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
//        Second first = new Second();
        First first = new First();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = first.minWindow(s, t);
        System.out.println(result);
    }

    private static class First {
        public String minWindow(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : t.toCharArray()) {
                map.merge(c, 1, Integer::sum);
            }
            int counter = t.length(), begin = 0, end = 0, diff = Integer.MAX_VALUE, head = 0, tail = 0;
            while (end < s.length()) {
                char e = s.charAt(end);
                if (map.merge(e, -1, Integer::sum) >= 0) {
                    counter--;
                }
                while (counter == 0) {
                    char b = s.charAt(begin);
                    if (end - begin < diff) {
                        head = begin;
                        tail = end + 1;
                        diff = end - head;
                    }
                    if (map.merge(b, 1, Integer::sum) > 0) {
                        counter++;
                    }
                    begin++;
                }
                end++;
            }
            return s.substring(head, tail);
        }
    }

    private static class Second {
        public String minWindow(String s, String t) {
            char[] charsS = s.toCharArray();
            char[] charsT = t.toCharArray();
            int[] freq = new int[128];
            for (char c : charsT) {
                freq[c]++;
            }
            int counter = charsT.length;
            int begin = 0;
            int end = 0;
            int d = Integer.MAX_VALUE;
            int head = 0;
            while (end < charsS.length) {
                if (freq[charsS[end++]]-- > 0) {
                    counter--;
                }
                while (counter == 0) {
                    if (end - begin < d) {
                        d = end - (head = begin);
                    }
                    if (freq[charsS[begin++]]++ == 0) {
                        counter++;
                    }
                }
            }
            return d == Integer.MAX_VALUE ? "" : s.substring(head, end);
        }
    }
}
