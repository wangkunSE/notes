package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/18
 * @time: 下午12:23
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ConsoleInput {

    public static void main(String[] args) {
        First first = new First();
        String console = first.console("abLcDhadRsBss");
        System.out.println(console);
    }

    private static class First {
        public String console(String source) {
            StringBuilder sb = new StringBuilder();
            char[] charSource = source.toCharArray();
            int cursor = 0;
            for (int i = 0; i < charSource.length; i++) {
                char temp = charSource[i];
                if (temp >= 'a' && 'z' >= temp) {
                    sb.insert(cursor, temp);
                    cursor += 1;
                } else if ('L' == temp) {
                    cursor = Math.max(0, cursor - 1);
                } else if ('R' == temp) {
                    cursor = Math.min(cursor + 1, sb.length() - 1);
                } else if ('B' == temp) {
                    if (cursor - 1 >= 0) {
                        sb.deleteCharAt(cursor - 1);
                        cursor -= 1;
                    }
                } else if ('D' == temp) {
                    if (cursor + 1 < sb.length()) {
                        sb.deleteCharAt(cursor + 1);
                        cursor += 1;
                    }
                }
            }

            return sb.toString();
        }
    }
}
