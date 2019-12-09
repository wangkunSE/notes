package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/12/9
 */
public class CombineSum_III_216 {

    public static void main(String[] args) {

        System.out.println(new CombineSum_III_216().combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {

        if (k <= 0 || n <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        findAllSums(result, tempResult, k, 1, n);
        return result;
    }

    private void findAllSums(List<List<Integer>> result, List<Integer> tempResult, int k, int start, int remain) {

        if (remain < 0) {
            return;
        }

        if (k == 0 && remain == 0) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        if (k <= 0) {
            return;
        }

        for (int i = start; i < 10; i++) {
            tempResult.add(i);
            findAllSums(result, tempResult, k - 1, i + 1, remain - i);
            tempResult.remove(tempResult.size() - 1);
        }


    }
}
