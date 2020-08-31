package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/4
 */
public class RemoveSortedDuplicateElements {

    private static class Solution {
        public int removeDuplicates(int[] nums) {
            if (null == nums || nums.length < 1) {
                return 0;
            }

            int pre = nums[0];
            int writer = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != pre) {
                    pre = nums[i];
                    nums[writer++] = nums[i];
                }
            }
            return writer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int element = solution.removeDuplicates(arr);
        System.out.println(element);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
