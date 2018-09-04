package com.soul.alg.leetcode;

import java.util.Arrays;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/9
 * @time: 上午9:58
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class RemoveSortedArray {

    public static void main(String[] args) {
        First first = new First();
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int length = first.removeDuplicates(nums);
        System.out.println(length);
    }

    private static class First {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }

            int result = 1;
            int count = 0;
            int temp = nums[0];
            int length = nums.length;

            for (int i = 1; i < length; i++) {
                if (temp == nums[i]) {
                    if (count < 1) {
                        count++;
                        result++;
                    } else {
                        count++;
                    }
                } else {
                    if (count > 1) {
                        int move = count - 1;
                        for (int j = i - move; j < length - move; j++) {
                            nums[j] = nums[j + move];
                        }
                        i -= move;
                        length -= move;
                    }
                    count = 0;
                    result++;
                }
                temp = nums[i];
            }
            return result;
        }
    }
}
