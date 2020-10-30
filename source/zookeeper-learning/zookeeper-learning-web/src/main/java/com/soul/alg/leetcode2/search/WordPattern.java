package com.soul.alg.leetcode2.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class WordPattern {
    static class Solution {
        public boolean wordPattern(String pattern, String str) {
            if (null == pattern || null == str) {
                return false;
            }
            Map<Character, String> pIndex = new HashMap<>();
            Map<String, Character> sIndex = new HashMap<>();

            char[] chars = pattern.toCharArray();
            String[] strings = str.split(" ");
            if (chars.length != strings.length) {
                return false;
            }

            for (int i = 0; i < chars.length; i++) {
                String s = pIndex.get(chars[i]);
                Character character = sIndex.get(strings[i]);
                if (Objects.isNull(s) && Objects.isNull(character)) {
                    pIndex.put(chars[i], strings[i]);
                    sIndex.put(strings[i], chars[i]);
                } else {
                    if (Objects.isNull(s) || Objects.isNull(character)) {
                        return false;
                    }

                    if (!s.equals(strings[i]) || !character.equals(chars[i])) {
                        return false;
                    }
                }

            }

            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.wordPattern("abba", "dog cat cat dog");
        System.out.println(b);
    }
}
