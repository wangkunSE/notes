package com.soul.alg.leetcode2.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * @author wangkunwk
 * @version 2020/9/12
 */
public class ClosestThreeSum {
    static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            if (Objects.isNull(nums)) {
                return 0;
            }
            Arrays.sort(nums);
            int result = nums[0];
            int diff = Integer.MAX_VALUE;

            for (int i = 0; i < nums.length - 2; i++) {
                if (i>0 && nums[i] == nums[i-1]){
                    continue;
                }
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int tmpResult = nums[i] + nums[low] + nums[high];
                    if (tmpResult < target) {
                        low++;

                    } else if (tmpResult > target) {
                        high--;
                    } else {
                        return target;
                    }

                    int tmpDiff = 0;
                    if (target > tmpResult) {
                        tmpDiff = target - tmpResult;
                    } else {
                        tmpDiff = tmpResult - target;
                    }

                    if (diff > tmpDiff) {
                        diff = tmpDiff;
                        result = tmpResult;
                    }
                }

            }
            return result;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 1, 1, 0};
        System.out.println(solution.threeSumClosest(arr, 100));
    }
}
