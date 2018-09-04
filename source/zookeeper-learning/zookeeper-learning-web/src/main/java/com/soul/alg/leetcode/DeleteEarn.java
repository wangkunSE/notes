package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/10
 * @time: 上午11:12
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class DeleteEarn {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {3, 1};
//        int max = first.deleteAndEarn(nums);
        int[] newInts = Arrays.copyOf(nums, nums.length);
        System.out.println(nums == newInts);
        System.out.println(newInts);
        nums[0]=-1;
        System.out.println(nums);
//        System.out.println(max);
    }

    private static class First {
        public int deleteAndEarn(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }
            if (nums.length < 2) {
                return nums[0];
            }

            Arrays.sort(nums);
            List<Integer> list = new ArrayList<>();

            int currentValue = nums[nums.length - 1];
            int temp = nums[nums.length - 1];
            int result = 0;

            for (int i = nums.length - 2; i >= 0; i--) {

                if (nums[i] == temp) {
                    currentValue += nums[i];
                } else {
                    if (nums[i] - 1 == temp || nums[i] + 1 == temp) {
                        list.add(currentValue);
                    } else {
                        result += currentValue;
                        currentValue = nums[i];
                        if (i == 0) {
                            result += currentValue;
                        }
                        temp = nums[i];
                        continue;
                    }
                    currentValue = nums[i];
                }
                if (i == 0) {
                    list.add(currentValue);
                }
                temp = nums[i];
            }

            int earn = 0;
            int notEarn = 0;
            for (int i = 0; i < list.size(); i++) {
                int current = earn;
                earn = Math.max(notEarn + list.get(i), earn);
                notEarn = Math.max(current, notEarn);
            }

            return result + Math.max(earn, notEarn);
        }
    }
}
