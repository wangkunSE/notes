package com.soul.alg.leetcode;

import java.util.Arrays;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/17
 * @time: 下午4:11
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ProductWithoutSelf {
    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();
        int[] nums = {1, 2, 3, 4};
        int[] result = second.productExceptSelf(nums);
        for (int si = 0; si < nums.length; si++) {
            System.out.println(result[si]);
        }
//        int[] ints = first.productExceptSelf(nums);
//        System.out.println(ints);
    }

    private static class First {
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int length = nums.length;
            long total = 1L;
            int zeroCount = 0;
            for (int i = 0; i < length; i++) {
                if (nums[i] != 0) {
                    total = nums[i] * total;
                } else {
                    zeroCount++;
                }
            }
            if (zeroCount > 1) {
                Arrays.fill(nums, 0);
                return nums;
            }

            for (int i = 0; i < length; i++) {
                int temp = nums[i];
                if (temp != 0) {
                    if (zeroCount > 0) {
                        nums[i] = 0;
                    } else {
                        nums[i] = (int) (total / temp);
                    }
                } else {
                    nums[i] = (int) total;
                }
            }
            return nums;
        }
    }

    private static class Second {
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int length = nums.length;
            int[] res = new int[length];
            res[0] = 1;
            for (int i = 1; i < length; i++) {
                res[i] = res[i - 1] * nums[i - 1];
            }
            int right = 1;
            for (int i = length - 1; i >= 0; i--) {
                res[i] = right * res[i];
                right = right * nums[i];
            }
            return res;
        }
    }
}
