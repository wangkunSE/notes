package com.soul.alg.tencet;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/30
 * @time: 下午4:49
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ArrayCopy {


    public static void main(String[] args) {

        ArrayCopy arrayCopy = new ArrayCopy();
        int[] nums = {0,1,2,3,4,5,6};
        arrayCopy.memCopy(nums,0,4,4);
        System.out.println();
    }

    public void memCopy(int[] nums, int start, int target, int size) {
        if (nums == null || start >= target || start > nums.length || target > nums.length
                || size < 0 || start + size > nums.length) {
            return;
        }

        for (int i = 0; i < size; i++) {
            nums[target++] = nums[start++];
        }
    }
}
