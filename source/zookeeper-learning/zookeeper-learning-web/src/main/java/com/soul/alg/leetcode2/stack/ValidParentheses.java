package com.soul.alg.leetcode2.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * @author wangkunwk
 * @version 2020/10/8
 */
public class ValidParentheses {

    static class Solution {

        private static final Map<Character, Character> mapping;
        private static final List<Character> list;

        static {
            mapping = new HashMap<>();
            mapping.put('}', '{');
            mapping.put(')', '(');
            mapping.put(']', '[');

            list = new ArrayList<>();
            list.add('{');
            list.add('[');
            list.add('(');
        }

        public boolean isValid(String s) {
            if (Objects.isNull(s) || s.length() == 0 || s.length() % 2 != 0) {
                return false;
            }

            LinkedList<Character> stack = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (list.contains(c)) {
                    stack.addFirst(c);
                } else {
                    Character character = mapping.get(c);
                    if (Objects.nonNull(character) && !stack.isEmpty()) {
                        Character first = stack.getFirst();
                        if (character.equals(first)) {
                            stack.removeFirst();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

    }

    static class SolutionII {
        public boolean isValid(String s) {
            int length = s.length();
            if (length % 2 != 0) {
                return false;
            }
            int count = 0;
            char[] braces = new char[length / 2];

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < length; i++) {
                char currentBrace = s.charAt(i);

                if (currentBrace == '(' || currentBrace == '[' || currentBrace == '{') {
                    if (count >= length / 2) {
                        return false;
                    }

                    braces[count] = currentBrace;
                    count++;
                } else {
                    if (count == 0) {
                        return false;
                    }

                    if ((currentBrace == ')' && braces[count - 1] != '(') ||
                            (currentBrace == '}' && braces[count - 1] != '{') ||
                            (currentBrace == ']' && braces[count - 1] != '[')) {
                        return false;
                    }
                    count--;
                    braces[count] = '0';
                }
            }

            return count == 0;
        }
    }
}
