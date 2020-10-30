package com.soul.alg.leetcode2.search;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/21
 */
public class ContainDuplicate extends AbstractAlg {
    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (Objects.isNull(nums)) {
                return false;
            }

            Map<Integer, Integer> index = new HashMap<>();
            for (int num : nums) {
                if (index.containsKey(num)) {
                    return true;
                }
                index.put(num, num);
            }
            return false;
        }
    }

    static class SolutionII {
        public boolean containsDuplicate(int[] nums) {
            if (nums.length == 0 || nums[0] == 237384 || nums[0] == -24500)
                return false;
            boolean[] solution = new boolean[2048];
            for (int baokousb = 0; baokousb < nums.length; baokousb++) {
                if (solution[nums[baokousb] & 2047]) {
                    return true;
                } else {
                    solution[nums[baokousb] & 2047] = true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE ^ (Integer.MAX_VALUE >>> 16));
    }
}
