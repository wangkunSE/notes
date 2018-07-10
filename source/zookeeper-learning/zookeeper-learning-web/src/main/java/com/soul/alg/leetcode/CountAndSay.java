package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/29
 * @time: 下午12:27
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class CountAndSay {
    public static void main(String[] args) {
        First first = new First();
        String s = first.countAndSay(50);
        System.out.println(s);
    }

    private static class First {
        public String countAndSay(int n) {
            if( n == 1){
                return "1";
            }
            String beforeLast = countAndSay(n-1);
            char[] temp = beforeLast.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < temp.length ; ){
                int count = 0;
                char curChar = temp[i];
                while(count + i < temp.length &&curChar == temp[count+i]){
                    count++;
                }
                sb.append(count+""+curChar);
                i += count;
            }
            return sb.toString();
        }
    }
}
