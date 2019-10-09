package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/9
 */
public class CombinationSum_II_40 {
    public static void main(String[] args) {

        System.out.println(new CombinationSum_II_40().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (null == candidates || candidates.length <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        findAllSums(result, target, candidates, new ArrayList<>(), 0);

        return result;

    }

    private void findAllSums(List<List<Integer>> result, int remain, int[] candidates,
                             List<Integer> tempSum, int offset) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            result.add(new ArrayList<>(tempSum));
        } else {

            for (int i = offset; i < candidates.length; i++) {
                int candidate = candidates[i];
                if (candidate > remain) {
                    break;
                }
                tempSum.add(candidate);
                findAllSums(result, remain - candidate, candidates, tempSum, i + 1);
                tempSum.remove(tempSum.size() - 1);

                while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                    i++;
                }
            }
        }

    }
}
