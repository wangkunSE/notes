package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/28
 * @time: 上午10:46
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SearchForARange {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        first.searchRange(nums, target);
    }

    private static class First {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length < 1) {
                return new int[]{-1, -1};
            }
            int length = nums.length;
            int index = binarySearch(nums, 0, length - 1, target);
            if (index == -1) {
                return new int[]{-1, -1};
            }
            int temp = index;
            int left, right;
            while (temp >= 0 && nums[index] == nums[temp]) temp--;
            left = temp + 1;
            temp = index;
            while (temp < length && nums[index] == nums[temp]) temp++;
            right = temp - 1;

            return new int[]{left, right};
        }

        private int binarySearch(int[] arr, int start, int end, int target) {
            int low = start, high = end;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }
    }
}
