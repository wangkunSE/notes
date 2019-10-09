package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/8
 */
public class Permutations_46 {

    public static void main(String[] args) {
        System.out.println(new Permutations_46().permute(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {

        if (null == nums || nums.length <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        fillResult(result, new ArrayList<>(), nums);

        return result;
    }

    private void fillResult(List<List<Integer>> result, List<Integer> tempPermute, int[] nums) {
        if (tempPermute.size() == nums.length) {
            result.add(new ArrayList<>(tempPermute));
            return;
        }

        for (int num : nums) {
            if (tempPermute.contains(num)) {
                continue;
            }
            tempPermute.add(num);
            fillResult(result, tempPermute, nums);
            tempPermute.remove(tempPermute.size() - 1);
        }

    }
}
