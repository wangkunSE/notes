package com.soul.alg.leetcode2.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c,
 * and d in nums such that a + b + c + d = target? Find all unique quadruplets
 * in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author wangkunwk
 * @version 2020/9/6
 */
public class FourSum {
    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (Objects.isNull(nums)) {
                return result;
            }

            Arrays.sort(nums);
            List<Integer> tmpResult = new ArrayList<>();
            findAllSums(result, tmpResult, nums, target, 0);
            return result;
        }

        private void findAllSums(List<List<Integer>> result, List<Integer> tmpResult, int[] nums, int target, int start) {
            if (start >= nums.length) {
                return;
            }

            if (target != 0 && tmpResult.size() == 4) {
                return;
            }

            if (target == 0 && tmpResult.size() == 4) {
                result.add(new ArrayList<>(tmpResult));
                return;
            }

            for (int i = start; i < nums.length; i++) {
                int curValue = nums[i];
                if (i != start && nums[i] == nums[i - 1]) {
                    continue;
                }
                tmpResult.add(curValue);
                findAllSums(result, tmpResult, nums, target - curValue, start + 1);
                tmpResult.remove(tmpResult.size() - 1);
            }

        }
    }

    static class SolutionII {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (Objects.isNull(nums)) {
                return result;
            }
            Arrays.sort(nums);


            for (int i = 0; i < nums.length - 3; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j != i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    int low = j + 1;
                    int high = nums.length - 1;

                    while (low < high) {
                        int sum = nums[low] + nums[high] + nums[i] + nums[j];
                        if (sum > target) {
                            high--;
                        } else if (sum < target) {
                            low++;
                        } else {
                            List<Integer> tmpResult = new ArrayList<>();
                            tmpResult.add(nums[i]);
                            tmpResult.add(nums[j]);
                            tmpResult.add(nums[low]);
                            tmpResult.add(nums[high]);
                            result.add(tmpResult);
                            low++;
                            high--;

                            while (low < high && nums[low] == nums[low - 1]) {
                                low++;
                            }
                            while (low < high && nums[high] == nums[high + 1]) {
                                high--;
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        int[] arr = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(solution.fourSum(arr, 0));

//        System.out.println(Long.valueOf(100_000_000*16_000L));
//        System.out.println(Long.valueOf(100_000_000*10_000L));
//        System.out.println(Long.valueOf(160_000_000*16_000L));
//        System.out.println(Long.valueOf(160_000_000_000_000L*16_000_000_000L));
    }
}
