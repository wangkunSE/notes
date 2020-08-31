package com.soul.alg.leetcode2.array;


import java.util.Arrays;

/**
 * @author wangkunwk
 * @version 2020/8/4
 */
public class MoveZeros {

    private static class Solution {
        public void moveZeroes(int[] nums) {

            if (nums == null) {
                return;
            }
            if (nums.length <= 1) {
                return;
            }

            int i = 0;
            int j = 0;

            while (i < nums.length && j < nums.length) {
                while (i < nums.length && nums[i] != 0) {
                    i++;
                }
                while ((j < nums.length && nums[j] == 0) || (j < i)) {
                    j++;
                }
                if (j < nums.length && i < j) {
                    swapNum(nums, i, j);
                }
            }

        }

        private void swapNum(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0,1,0,3,12};
        solution.moveZeroes(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
