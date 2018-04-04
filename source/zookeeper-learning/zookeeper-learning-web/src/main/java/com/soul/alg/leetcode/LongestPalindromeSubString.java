package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkun1
 * @version 2018/4/3
 */
public class LongestPalindromeSubString {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        Integer maxIndex = -1;
        Map<Integer, String> indexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (indexMap.get(maxIndex) != null && indexMap.get(maxIndex).length() >= s.length() - 1) {
                break;
            }
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j == i + 1) {
                        indexMap.put(i, s.substring(i, i + 2));
                        if (indexMap.get(maxIndex) == null || indexMap.get(i).length() > indexMap.get(maxIndex).length()) {
                            maxIndex = i;
                        }
                    }
                    boolean flag = true;
                    for (int k = i + 1, h = j - 1; k <= h; k++, h--) {
                        if (s.charAt(k) != s.charAt(h)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        indexMap.put(i, s.substring(i, j + 1));
                        if (indexMap.get(maxIndex) == null || indexMap.get(i).length() > indexMap.get(maxIndex).length()) {
                            maxIndex = i;
                        }
                    }
                }
                if (j == s.length() - 1 && s.charAt(i) != s.charAt(j)) {
                    if (!indexMap.containsKey(i)) {
                        indexMap.put(i, s.substring(i, i + 1));
                        if (indexMap.get(maxIndex) == null || indexMap.get(i).length() > indexMap.get(maxIndex).length()) {
                            maxIndex = i;
                        }
                    }
                }
            }
        }
        return indexMap.get(maxIndex);
    }

    public static void main(String[] args) {
//        String test = new LongestPalindromeSubString().longestPalindrome(str);
////        System.out.println(str.charAt(5));
////        System.out.println(str.substring(4, 6));
////        System.out.println(str.charAt(4));
//        System.out.println(test);
////        System.out.println("");
        long startTime = System.currentTimeMillis();
        String str = "aaaaaaaaaaaaaaaaaaaaa";
        System.out.println(new LongestPalindromeSubString().longestPalindrome(str));
        long endTime = System.currentTimeMillis();
        System.out.println("花费了：" + (endTime - startTime));
    }

    public String dpSoultion(String s) {
        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }
}
