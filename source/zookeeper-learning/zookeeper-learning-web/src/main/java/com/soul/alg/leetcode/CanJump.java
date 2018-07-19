package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/16
 * @time: 上午11:52
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class CanJump {
    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();
        int[] nums = {3,2,1,1,4};
//        boolean b = first.canJump(nums);
        System.out.println(second.canJump(nums));

    }

    private static class First {
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length < 1){
                return false;
            }
            int length = nums.length;
            if(length == 1 && nums[0] == 0){
                return false;
            }
            for( int i = 0; i<length; i++){
                if(nums[i] == 0){
                    int tempI = i - 1;
                    boolean flag = false;
                    for(; tempI >= 0 ; tempI --){
                        if( nums[tempI] > (i - tempI)){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private static class Second {
        public boolean canJump(int[] nums) {
            int lastPos = nums.length - 1;
            for(int i = nums.length - 1; i >= 0; i--){
                if(i + nums[i] >= lastPos){
                    lastPos = i;
                }
            }
            return lastPos == 0;
        }
    }
}
