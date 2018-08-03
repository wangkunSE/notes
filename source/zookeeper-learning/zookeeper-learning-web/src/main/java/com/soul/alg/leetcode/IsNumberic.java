package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/2
 * @time: 下午4:33
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class IsNumberic {
    public static void main(String[] args) {
        First first = new First();
        boolean number = first.isNumber(".1");
        System.out.println(number);
    }

    private static class First {
        public boolean isNumber(String s) {
            if(s == null || s.length() < 1){
                return false;
            }

            char[] chars = s.toCharArray();

            int count = 0;
            boolean flag = false;
            for(int i = 0; i < s.length();i++){
                char temp = chars[i];
                if( temp == ' '){
                    continue;
                }

                if( temp == '-'){
                    if(count > 0){
                        return false;
                    }
                    continue;
                }
                if(temp == '.'){
                    if(count == 0 || flag){
                        return false;
                    }
                    flag = true;
                    continue;
                }

                if(temp == 'e'){
                    if(count == 0 || flag){
                        return false;
                    }
                    continue;
                }

                if(temp >= '0' && temp <='9'){
                    count++;
                }else{
                    return false;
                }
            }

            return count>0;
        }
    }
}
