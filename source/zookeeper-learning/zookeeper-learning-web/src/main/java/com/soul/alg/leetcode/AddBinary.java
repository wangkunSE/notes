package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/2
 * @time: 下午5:45
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class AddBinary {
    public static void main(String[] args) {
        First first = new First();
        String result = first.addBinary("101111", "10");
        System.out.println(result);
    }

    private static class First {
        public String addBinary(String a, String b) {

            if (a.length() > b.length()) {
                return addBinary(b, a);
            }

            boolean over = false;
            StringBuilder sb = new StringBuilder();
            for (int i = a.length() - 1; i >= 0; i--) {
                char aTemp = a.charAt(i);
                char bTemp = b.charAt(i + b.length() - a.length());
                if (over) {
                    if (aTemp == '1' && bTemp == '1') {
                        sb.append("1");
                    } else if (aTemp == '1' || bTemp == '1') {
                        sb.append("0");
                    } else {
                        sb.append("1");
                        over = false;
                    }
                } else {
                    if (aTemp == '1' && bTemp == '1') {
                        over = true;
                        sb.append("0");
                    } else if (aTemp == '1' || bTemp == '1') {
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                }
            }
            for (int i = b.length() - a.length() - 1; i >= 0; i--) {
                char bTemp = b.charAt(i);
                if (over) {
                    if (bTemp == '1') {
                        sb.append("0");
                    } else {
                        sb.append("1");
                    }
                } else {
                    sb.append(bTemp);
                }
            }
            if (over) {
                sb.append("1");
            }

            return sb.reverse().toString();
        }
    }
}
