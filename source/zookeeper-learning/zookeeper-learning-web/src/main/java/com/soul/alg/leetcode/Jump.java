package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/17
 * @time: 上午11:15
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Jump {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {2,3,1,1,4};
        int jump = first.jump(nums);
        System.out.println(jump);
    }

    private static class First {
        public int jump(int[] nums) {
            if(nums== null || nums.length < 2){
                return 0;
            }
            // if(nums.length == 2){
            //     return 1;
            // }
            int count = 0;
            int length = nums.length;
            int lastPos = length -1;
            int minIndex = length -1;

            for(int i = length - 2; i >= 0;){
                for(int j = i ; j >= 0; j--){
                    if(nums[j] + j >= lastPos && minIndex > j){
                        minIndex = j;
                    }
                }
                lastPos = minIndex;
                if(minIndex >= 0){
                    count ++;
                }
                if (minIndex == 0){
                    break;
                }
                i = minIndex;
            }
            return count;
        }
    }
}
