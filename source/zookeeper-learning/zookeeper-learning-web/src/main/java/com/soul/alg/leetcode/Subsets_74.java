package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/9/25
 */
public class Subsets_74 {

    public static void main(String[] args) {
        List<List<Integer>> subsets = new Subsets_74().subsets(new int[]{1, 2, 3, 4});
        System.out.println(subsets);
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (null == nums || nums.length < 1) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> localSet = new ArrayList<>();
        result.add(new ArrayList<>());

        fillResultForNums(result, nums, localSet, 0);
        return result;
    }

    private void fillResultForNums(List<List<Integer>> result, int[] nums, List<Integer> localSet, int offset) {
        if (offset >= nums.length) {
            return;
        }

        //pick
        int val = nums[offset];
        localSet.add(val);
        fillResultForNums(result, nums, localSet, offset + 1);
        result.add(new ArrayList<>(localSet));

        //not pick
        localSet.remove(localSet.size() - 1);
        fillResultForNums(result, nums, localSet, offset + 1);
    }
}
