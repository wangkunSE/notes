package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/6
 * @time: 上午10:29
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Permutations {
    public static void main(String[] args) {

        First first = new First();
        int[] nums = {1,2,3};
        List<List<Integer>> permute = first.permute(nums);
        System.out.println(permute);
    }

    private static class First {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            // Arrays.sort(nums); // not necessary
            backtrack(list, new ArrayList<>(), nums);
            return list;
        }

        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
            if(tempList.size() == nums.length){
                list.add(new ArrayList<>(tempList));
            } else{
                for(int i = 0; i < nums.length; i++){
                    if(tempList.contains(nums[i])) continue; // element already exists, skip
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
}
