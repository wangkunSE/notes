package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/5
 */
public class RemoveSortedDuplicateElementsII {

    private static class Solution {
        public int removeDuplicates(int[] nums) {
            if (null == nums || nums.length < 1) {
                return 0;
            }

            int preVal = nums[0];
            int writer = 1;
            int count = 0;
            for (int i = 1; i < nums.length; i++) {
                if (preVal != nums[i]) {
                    nums[writer++] = nums[i];
                    preVal = nums[i];
                    count = 0;
                } else if (preVal == nums[i] && count < 1) {
                    nums[writer++] = nums[i];
                    count++;
                }
            }

            return writer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1,1,1,2,2,3};
        int element = solution.removeDuplicates(arr);
        System.out.println(element);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
