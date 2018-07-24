package com.soul.alg.leetcode;

import java.util.Arrays;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/20
 * @time: 下午2:57
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class BulbSwitch {

    public static void main(String[] args) {
        First first = new First();
        for (int i = 1; i < 20; i++) {
            first.bulbSwitch(i);
        }
    }

    private static class First {
        public int bulbSwitch(int n) {
            int[] bulbs = new int[n];
            Arrays.fill(bulbs, 0);
            for (int i = 1; i < n + 1; i++) {
                if (i > (n + 1) / 2) {
                    toggleSingle(bulbs, i);
                    continue;
                }
                toggle(bulbs, i);
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                if (bulbs[i] == 1) {
                    result++;
                }
            }
            return n < 1 ? 0 : result;
        }

        private void toggle(int[] nums, int mul) {
            for (int i = mul, j = 1; i < nums.length + 1; j++, i = mul * j) {
                nums[i - 1] = nums[i - 1] == 0 ? 1 : 0;
            }
        }

        private void toggleSingle(int[] nums, int mul) {
            nums[mul - 1] = nums[mul - 1] == 0 ? 1 : 0;
        }
    }
}
