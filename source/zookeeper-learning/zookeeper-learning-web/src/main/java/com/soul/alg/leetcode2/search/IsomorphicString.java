package com.soul.alg.leetcode2.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class IsomorphicString {
    static class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (Objects.isNull(s) || Objects.isNull(t)) {
                return false;
            }

            if (s.length() != t.length()) {
                return false;
            }

            Map<Character, Character> sIndex = new HashMap<>();
            Map<Character, Character> tIndex = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                Character tChar = sIndex.get(s.charAt(i));
                Character sChar = tIndex.get(t.charAt(i));
                if (Objects.isNull(tChar) && Objects.isNull(sChar)) {
                    sIndex.put(s.charAt(i), t.charAt(i));
                    tIndex.put(t.charAt(i), s.charAt(i));
                } else {
                    if (Objects.isNull(tChar) || Objects.isNull(sChar)) {
                        return false;
                    }

                    if (!sChar.equals(s.charAt(i)) || !tChar.equals(t.charAt(i))) {
                        return false;
                    }
                }

            }

            return true;


        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean isomorphic = solution.isIsomorphic("egg", "odd");
        System.out.println(isomorphic);
    }

}
