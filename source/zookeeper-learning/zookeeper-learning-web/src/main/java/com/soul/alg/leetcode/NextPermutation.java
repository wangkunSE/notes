package com.soul.alg.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/26
 * @time: 下午3:14
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class NextPermutation {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {1,3,2};
        first.nextPermutation(nums);
    }

    private static class First {
        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int length = nums.length;
            int tail = Integer.MAX_VALUE;
            int right = length - 1;
            int rightMin = Integer.MAX_VALUE;
            while (right > 0) {
                if (nums[right] > nums[right - 1]) {
                    break;
                }
                right--;
            }
            int index = length - 1;
            while (index >= right && right > 0) {
                if (rightMin > nums[index] && nums[index] > nums[right - 1]) {
                    rightMin = nums[index];
                    tail = index;
                }
                index--;
            }
            if (right > 0) {
                swap(nums, tail, right - 1);
                reverseSort(nums, right, length);
            } else {
                reverseSort(nums, 0, length);
            }
        }

        private void reverseSort(int[] nums, int start, int end) {
            for (int i = 0; i < (end - start) / 2; i++) {
                swap(nums, start + i, end - i - 1);
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
