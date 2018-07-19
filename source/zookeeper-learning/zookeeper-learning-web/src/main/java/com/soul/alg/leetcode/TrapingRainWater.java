package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/19
 * @time: 下午5:10
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class TrapingRainWater {
    public static void main(String[] args) {
        First first = new First();
        int[] height = {5, 4, 1, 2};
        int trap = first.trap(height);
        System.out.println(trap);
    }

    private static class First {
        public int trap(int[] height) {
            if (height == null || height.length < 3) {
                return 0;
            }
            int total = getTheTotalWater(height, 0, height.length, 0);
            return total;
        }

        private int getTheTotalWater(int[] height, int start, int end, int index) {
            if (start < end - 1) {
                int containsLeft = 0;
                int containsRight = 0;
                //find current max value index
                int max = findTheMax(height, start, end, index);
                //find left second max value index
                int left = findTheMax(height, start, max, max);
                //find right second max value index
                int right = findTheMax(height, max + 1, end, max);
                //left water capcity
                containsLeft = getTheWater(height, left, max, height[left]);
                if (right < end) {
                    //right water capcity
                    containsRight = getTheWater(height, max, right + 1, height[right]);
                }
                //find outward
                return getTheTotalWater(height, start, left + 1, left)
                        + getTheTotalWater(height, right, end, right)
                        + containsLeft + containsRight;
            }
            return 0;
        }

        private int findTheMax(int[] nums, int start, int end, int prevMaxIndex) {
            int max = Integer.MIN_VALUE;
            int index = start;
            int dist = 0;
            for (int i = start; i < end; i++) {
                int tempDis = Math.abs(prevMaxIndex - i);
                if (max < nums[i]) {
                    max = nums[i];
                    index = i;
                } else if (max == nums[i] && dist < tempDis) {
                    index = i;
                    dist = tempDis;
                }
            }
            return index;
        }

        private int getTheWater(int[] nums, int start, int end, int height) {
            int total = 0;
            for (int i = start; i < end; i++) {
                if (nums[i] <= height) {
                    total += (height - nums[i]);
                }
            }
            return total;
        }
    }
}
