package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/17
 */
public class Subset_II_90 {
    public static void main(String[] args) {
        System.out.println(new Subset_II_90().subsetsWithDup(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        if (null == nums || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        findAllSubsetsWithDup(result, new ArrayList<Integer>(), nums, used, 0);

        return result;
    }

    private void findAllSubsetsWithDup(List<List<Integer>> result, List<Integer> tempSubset, int[] nums, boolean[] used, int start) {
        if (start == nums.length) {
            result.add(new ArrayList<>(tempSubset));
            return;
        }

        if (!used[start] && !(start > 0 && nums[start] == nums[start - 1] && !used[start - 1])) {
            used[start] = true;
            tempSubset.add(nums[start]);
            findAllSubsetsWithDup(result, tempSubset, nums, used, start + 1);
            tempSubset.remove(tempSubset.size() - 1);
            used[start] = false;
        }

        findAllSubsetsWithDup(result, tempSubset, nums, used, start + 1);
    }
}
