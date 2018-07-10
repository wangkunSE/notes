package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = first.subsets(nums);
        System.out.println(subsets);
    }

    private static class First {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(list, new ArrayList<>(), nums, 0);
            return list;
        }

        private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
            list.add(new ArrayList<>(tempList));
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
