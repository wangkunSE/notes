package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/5
 */
public class SortedColor {
    private static class Solution {
        public void sortColors(int[] nums) {
            if (null == nums || nums.length < 1) {
                return;
            }

            int zeroCount = 0;
            int twoCount = 0;
            for (int num : nums) {
                if (num == 0) {
                    zeroCount++;
                }
                if (num == 2) {
                    twoCount++;
                }
            }

            for (int i = 0; i < nums.length; i++) {
                while (i < zeroCount) {
                    nums[i++] = 0;
                }

                int j = 0;
                while (j < (nums.length - zeroCount - twoCount)) {
                    nums[i++] = 1;
                    j++;
                }

                int k = 0;
                while (k < twoCount) {
                    nums[i++] = 2;
                    k++;
                }
            }


        }
    }

    private static class Solution2{
        public void sortColors(int[] nums) {
            if (nums == null || nums.length < 1) {
                return;
            }

            int low = 0;
            int high = nums.length - 1;
            for (int i = 0; low < high && i < nums.length; ) {
                if (nums[i] == 0 && i > low) {
                    while (low < high && nums[low] == 0) {
                        low++;
                    }
                    if (i > low) {
                        swap(nums, i, low);
                    }
                    continue;
                } else if (nums[i] == 2 && i < high) {
                    while (low < high && nums[high] == 2) {
                        high--;
                    }
                    if (i < high){
                        swap(nums, i, high);
                    }
                    continue;
                }
                i++;
            }
        }
        private void swap(int[] arr,int i,int j){

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0,1,0,1,2,0,1};
        solution.sortColors(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
