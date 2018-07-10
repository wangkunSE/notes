package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/9
 * @time: 上午10:09
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class PermutationsII {
    public static void main(String[] args) {
        First first = new First();
        int[] nums = {1,2,1};
        List<List<Integer>> lists = first.permuteUnique(nums);
        System.out.println(lists);
    }

    private static class First {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
            return list;
        }

        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
            if(tempList.size() == nums.length){
                list.add(tempList);
            } else{
                for(int i = 0; i < nums.length; i++){
                    if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                    used[i] = true;
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums, used);
                    used[i] = false;
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
}
