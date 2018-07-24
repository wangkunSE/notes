package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/24
 * @time: 下午4:04
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SortColor {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {1,2,0};
        first.sortColors(nums);
        System.out.println(nums);
    }

    private static class First {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length < 1) {
                return;
            }

            int low = 0;
            int high = nums.length - 1;
            for (int i = 0; low < high && i < nums.length; ) {
                if (nums[i] == 0 && i > low) {
                    while (low < high && nums[low] == 0) {
                        low++;
                    }
                    if (i > low) {
                        swap(nums, i, low);
                    }
                    continue;
                } else if (nums[i] == 2 && i < high) {
                    while (low < high && nums[high] == 2) {
                        high--;
                    }
                    if (i < high){
                        swap(nums, i, high);
                    }
                    continue;
                }
                i++;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
