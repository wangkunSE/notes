package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/5
 * @time: 上午10:51
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SubSet {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = first.subsets(nums);
        System.out.println(subsets);
    }

    private static class First {
        public List<List<Integer>> subsets(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return new ArrayList<>();
            }

            List<List<Integer>> result = new ArrayList<>();
            List<Integer> tempList = new ArrayList<>();
            result.add(new ArrayList<>());
            findAllSubsets(result, tempList, nums, 0);

            return result;
        }

        private void findAllSubsets(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
            if (start == nums.length) {
                return;
            } else {

                tempList.add(nums[start]);
                result.add(new ArrayList<>(tempList));
                findAllSubsets(result, tempList, nums, start + 1);
                tempList.remove(tempList.size() - 1);

                findAllSubsets(result, tempList, nums, start + 1);
            }
        }


    }
}
