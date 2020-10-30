package com.soul.alg.leetcode2.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class ThreeSum {

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (Objects.isNull(nums)) {
                return null;
            }
            Arrays.sort(nums);
            int target = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i>0&& nums[i] == nums[i-1]){
                    continue;
                }
                List<Integer> tmpResult = new ArrayList<>();
                target = -nums[i];
                tmpResult.add(nums[i]);
                int low = i + 1;
                int high = nums.length - 1;

                while (low < high) {
                    int sum = nums[low] + nums[high];
                    if (sum > target) {
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else {
                        tmpResult.add(nums[low]);
                        tmpResult.add(nums[high]);
                        result.add(new ArrayList<>(tmpResult));
                        tmpResult = new ArrayList<>();
                        tmpResult.add(-target);
                        low++;
                        while (low < high && nums[low - 1] == nums[low]) {
                            low++;
                        }
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0, 0, 0, 0};
        List<List<Integer>> lists = solution.threeSum(arr);
        System.out.println(lists);
    }
}
