package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/26
 * @time: 下午3:43
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class RegexMatch {
    public static void main(String[] args) {
        First first = new First();
        boolean asdasd = first.isMatch("asdasd", "asda*sd");
        System.out.println(asdasd);
    }

    private static class First {
        public boolean isMatch(String s, String p) {

            if (s == null || p == null) {
                return false;
            }
            boolean[][] dp = new boolean[s.length()+1][p.length()+1];
            dp[0][0] = true;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '*' && dp[0][i-1]) {
                    dp[0][i+1] = true;
                }
            }
            for (int i = 0 ; i < s.length(); i++) {
                for (int j = 0; j < p.length(); j++) {
                    if (p.charAt(j) == '.') {
                        dp[i+1][j+1] = dp[i][j];
                    }
                    if (p.charAt(j) == s.charAt(i)) {
                        dp[i+1][j+1] = dp[i][j];
                    }
                    if (p.charAt(j) == '*') {
                        if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                            dp[i+1][j+1] = dp[i+1][j-1];
                        } else {
                            dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                        }
                    }
                }
            }
            return dp[s.length()][p.length()];
        }
    }
}
