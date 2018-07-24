package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/20
 * @time: 下午12:12
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MaxArea {
    public static void main(String[] args) {
        First first = new First();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = first.maxArea(height);
        System.out.println(maxArea);
    }

    private static class First {
        public int maxArea(int[] height) {
            if (height == null || height.length < 2) {
                return 0;
            }

            int maxCap = 0;
            int low = 0;
            int high = height.length - 1;
            while (low < high) {
                int curLow = height[low];
                int curHigh = height[high];
                int temp = curLow > curHigh ? curHigh : curLow;
                int curResult = temp * (high - low);
                if (curResult > maxCap) {
                    maxCap = curResult;
                }
                if (curLow > curHigh) {
                    while (low < high && curHigh >= height[high]) {
                        high--;
                    }
                } else {
                    while (low < high && curLow >= height[low]) {
                        low++;
                    }
                }
            }
            return maxCap;
        }
    }
}
