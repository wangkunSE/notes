package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkun1
 * @version 2018/4/2
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        int result = 0;
        int temp = 0;
        int tempIndex = 0;
        char[] chars = s.toCharArray();
        Map<Character, Boolean> existMap = new HashMap<>();
        for (int i = 0; i < chars.length; ) {
            if (existMap.get(chars[i]) != null && existMap.get(chars[i])) {
                existMap = new HashMap<>();
                if (temp > result) {
                    result = temp;
                }
                i = i - temp + 1;
                temp = 0;
            } else {
                existMap.put(chars[i], true);
                temp++;
                i++;
            }
        }
        if (temp != 0) {
            if (temp > result) {
                result = temp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int length = new LongestSubstring().lengthOfLongestSubstring("asdasdasdasdweq");
        System.out.println(length);
    }
}
