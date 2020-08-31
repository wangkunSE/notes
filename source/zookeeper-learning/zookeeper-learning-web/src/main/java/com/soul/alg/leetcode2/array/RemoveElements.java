package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/4
 */
public class RemoveElements {

    private static class Solution {
        public int removeElement(int[] nums, int val) {
            if (null == nums || nums.length < 1) {
                return 0;
            }

            int reader = 0;
            int count = 0;
            int writer = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i]!=val){
                    nums[writer++] = nums[reader];
                }else {
                    count++;
                }
                reader++;
            }
            return writer-1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0,1,2,2,3,0,4,2};
        int element = solution.removeElement(arr, 2);
        System.out.println(element);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
