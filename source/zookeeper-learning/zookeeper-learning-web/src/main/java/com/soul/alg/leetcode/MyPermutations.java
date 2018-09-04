package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/21
 * @time: 上午10:59
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MyPermutations {
    public static void main(String[] args) {
//        First first = new First();
//        first.init(3);
//        for (int[] ints : first.result) {
//            for (int i = 0; i < ints.length; i++) {
//                System.out.print(ints[i] + "\t");
//            }
//            System.out.println();
//        }
        String s = "asd";
        String substring = s.substring(1, 2);
        System.out.println(substring);
    }

    private static class First {
        public List<int[]> result = new ArrayList<>();
        private int n = 0;
        private int size = 0;

        public void init(int size) {
            int[] nums = new int[size];
            this.size = size;
            for (int i = 0; i < size; i++) {
                nums[i] = i + 1;
            }
            backtrace(result, nums, 0);
        }

        private void backtrace(List<int[]> list, int[] nums, int start) {
            if (start == nums.length - 1) {
                list.add(Arrays.copyOf(nums, nums.length));
                return;
            }

            if (start < nums.length) {
                for (int i = start; i < size; i++) {
                    swap(nums, i, start);
                    backtrace(list, nums, start + 1);
                    swap(nums, start, i);
                }
            }
        }

        private void swap(int[] nums, int from, int to) {
            int temp = nums[from];
            nums[from] = nums[to];
            nums[to] = temp;
        }
    }
}
