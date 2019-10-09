package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/9/27
 */
public class CombinationSum_39 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(new CombinationSum_39().combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (null == candidates || candidates.length <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        fillResultWithTemp(candidates, target, result, temp, 0);
        return result;
    }

    private void fillResultWithTemp(int[] candidates, int target, List<List<Integer>> result, List<Integer> temp, int offset) {
        if (0 > target) {
        } else if (0 == target) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = offset; i < candidates.length; i++) {
                int curNum = candidates[i];
                temp.add(curNum);
                fillResultWithTemp(candidates, target - curNum, result, temp, i);
                temp.remove(temp.size() - 1);
            }
        }


    }

}
