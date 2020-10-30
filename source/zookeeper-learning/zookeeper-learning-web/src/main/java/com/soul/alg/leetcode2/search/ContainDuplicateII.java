package com.soul.alg.leetcode2.search;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkunwk
 * @version 2020/9/21
 */
public class ContainDuplicateII extends AbstractAlg {

    static class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {

            if (nums == null || k < 1) {
                return false;
            }

            Map<Integer, Integer> index = new HashMap<>();

            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int curNum = nums[i];
                if (index.containsKey(curNum)) {
                    Integer pre = index.get(curNum);
                    minLen = Math.min(minLen, i - pre);
                    if (minLen <= k) {
                        return true;
                    }

                }
                index.put(curNum, i);
            }

            return false;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 2, 3, 1};
        System.out.println(solution.containsNearbyDuplicate(arr, 3));
    }
}
