package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/27
 * @time: 下午3:58
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class LongestValidParenheses {
    public static void main(String[] args) {
        First first = new First();
        int parentheses = first.longestValidParentheses(")()())");
        System.out.println(parentheses);
    }

    private static class First {
        public int longestValidParentheses(String s) {
            if (null == s || s.length() < 2) {
                return 0;
            }

            int length = s.length();
            int count = 0;
            int result = 0;
            char[] seq = s.toCharArray();
            Map<Integer, Integer> map = new HashMap<>(length / 2);
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < length; i++) {
                if (seq[i] == '(') {
                    stack.push(i);
                } else if (seq[i] == ')') {
                    if (stack.size() > 0) {
                        int leftIndex = stack.pop();
                        map.put(leftIndex, i);
                    }

                }
            }
            if (!map.isEmpty()) {
                for (int i = 0; i < length; ) {
                    while (map.get(i) != null && i < length) {
                        Integer rightIndex = map.get(i);
                        count += rightIndex - i + 1;
                        i += rightIndex - i + 1;
                    }
                    if (count > result) {
                        result = count;
                    }
                    count = 0;
                    i++;
                }
            }
            return result;
        }
    }
}
