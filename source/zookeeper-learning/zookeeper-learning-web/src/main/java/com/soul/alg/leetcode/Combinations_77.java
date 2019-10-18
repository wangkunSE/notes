package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/14
 */
public class Combinations_77 {

    public static void main(String[] args) {
        System.out.println(new Combinations_77().combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempCombine = new ArrayList<>();

        findAllCombines(result, tempCombine, k, n, 1);
        return result;
    }

    private void findAllCombines(List<List<Integer>> result, List<Integer> tempCombine, int k, int n, int offset) {
        if (k == 0) {
            result.add(new ArrayList<>(tempCombine));
            return;
        }

        for (int i = offset; i < n - (k - tempCombine.size()) + 1; i++) {
            tempCombine.add(i);
            findAllCombines(result, tempCombine, k - 1, n, i + 1);
            tempCombine.remove(tempCombine.size() - 1);
        }

    }
}
