package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/27
 * @time: 下午7:39
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        First first = new First();
        int[] nums = {2, 3, 4, 5, 6, 7, 8, 9, 1};
//        int[] nums = {3,1,2};
        int target = 9;
        int search = first.search(nums, target);
        System.out.println(search);
    }

    private static class First {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int length = nums.length;
            if (nums[0] <= nums[length - 1]) {
                return binary(nums, 0, length - 1, target);
            }
            int low = 0, high = length - 1, index;
            int mid = (length - 1) / 2;
            if (nums[mid] > nums[0]) {
                index = getTheSplitPoint(nums, mid, length - 1);
            } else {
                index = getTheSplitPoint(nums, 0, mid);
            }
            int result = -1;
            if (nums[0] > target) {
                result = binary(nums, index + 1, length - 1, target);
            } else {
                result = binary(nums, 0, index, target);
            }
            return result;
        }

        private int binary(int[] arr, int start, int end, int target) {
            int low = start, high = end;
            while (high >= low) {
                int middle = (high - low) / 2 + low;
                if (arr[middle] == target) {
                    return middle;
                } else if (arr[middle] < target) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
            return -1;
        }

        private int getTheSplitPoint(int[] arr, int start, int end) {
            int low = start, high = end;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (arr[mid] > arr[mid + 1]) {
                    return mid;
                }
                if (arr[low] < arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            if (arr[low] < arr[low + 1]) {
                return low + 1;
            }
            return low;
        }
    }
}
