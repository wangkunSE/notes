package com.soul.alg.leetcode2.search;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class TwoSum extends AbstractAlg {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null) {
                return new int[]{};
            }
            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer integer = index.get(nums[i]);
                if (Objects.nonNull(integer)) {
                    return new int[]{integer, i};
                } else {
                    index.put(target - nums[i], i);
                }
            }

            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{2, 7, 11, 15};
        int[] ints = solution.twoSum(arr, 9);
        printArr(ints);
    }
}
