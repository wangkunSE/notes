package com.soul.alg.huawei;

import java.util.Scanner;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/29
 * @time: 下午7:27
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Sum {
    public static void main(String[] args) {
        Second second = new Second();
        second.countTheCow();
    }

    private static class First {
        public int getSum(String str) {

            if (str == null || str.length() < 1) {
                return 0;
            }

            int sum = 0;
            int start = 0;
            int end = 0;
            boolean flag = false;
            for (int i = 0; i < str.length(); ) {
                char temp = str.charAt(i);
                if (temp == '-' && i < str.length() - 1 && isNumber(str.charAt(i + 1))) {
                    flag = true;
                    i++;
                } else if (isNumber(temp)) {
                    start = i;
                    end = i;
                    while (i < str.length() && isNumber(temp)) {
                        i++;
                        if (i < str.length()) {
                            end++;
                            temp = str.charAt(i);
                        }
                    }
                    if (i == str.length()) {
                        end = str.length();
                    }
                    String substring = str.substring(start, end);
                    if (!"".equals(substring)) {
                        int tempSum = Integer.parseInt(substring);
                        if (flag) {
                            tempSum = -tempSum;
                        }
                        sum += tempSum;
                        flag = false;
                    }
                } else {
                    i++;
                }
            }


            return sum;
        }

        private boolean isNumber(char temp) {
            return temp >= '0' && temp <= '9';
        }
    }

    private static class Second {
        public void countTheCow() {
            Scanner scanner = new Scanner(System.in);
            int n = Integer.parseInt(scanner.nextLine());
            while (n > 0) {
                int m = Integer.parseInt(scanner.nextLine());
                int k = Integer.parseInt(scanner.nextLine());
                count(m, k);
                n--;
            }
        }

        private void count(int initCount, int month) {

            int currentCow = initCount;
            int oneMonthCow = initCount;
            int twoMonthCow = 0;
            int threeMonthCow = 0;

            for (int i = 1; i < month; i++) {
                currentCow = currentCow + threeMonthCow;
                threeMonthCow = twoMonthCow;
                twoMonthCow = oneMonthCow;
                oneMonthCow = currentCow;
            }
            System.out.println(currentCow + oneMonthCow + twoMonthCow + threeMonthCow);
        }
    }
}
