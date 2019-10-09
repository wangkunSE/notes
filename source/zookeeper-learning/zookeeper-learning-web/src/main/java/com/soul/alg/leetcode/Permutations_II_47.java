package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/8
 */
public class Permutations_II_47 {
    public static void main(String[] args) {
        System.out.println(new Permutations_II_47().permuteUnique(new int[]{1, 1, 1, 2}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        findAllPermuteUnique(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void findAllPermuteUnique(List<List<Integer>> result, List<Integer> tempPermute, int[] nums, boolean[] used) {
        if (tempPermute.size() == nums.length) {
            result.add(new ArrayList<>(tempPermute));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            tempPermute.add(nums[i]);
            findAllPermuteUnique(result, tempPermute, nums, used);
            used[i] = false;
            tempPermute.remove(tempPermute.size() - 1);
        }
    }
}
