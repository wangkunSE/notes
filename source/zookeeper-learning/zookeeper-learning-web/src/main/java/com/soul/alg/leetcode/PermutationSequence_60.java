package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/10
 */
public class PermutationSequence_60 {

    public static void main(String[] args) {

        System.out.println(new PermutationSequence_60().getPermutation(3, 1));
    }

    public String getPermutation(int n, int k) {

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        StringBuilder sb = new StringBuilder();
        int[] types = new int[n + 1];
        types[0] = 1;
        for (int i = 1; i <= n; i++) {
            types[i] = i * types[i - 1];
        }

        k--;
        getPermutationSeq(n, k, nums, sb, types);
        return sb.toString();
    }

    private void getPermutationSeq(int n, int k, List<Integer> used, StringBuilder sb, int[] types) {
        if (n == 0) {
            return;
        }

        int curIndex = k / types[n - 1];
        sb.append(used.get(curIndex));
        used.remove(curIndex);

        getPermutationSeq(n - 1, k - curIndex * types[n - 1], used, sb, types);
    }
}
