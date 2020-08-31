package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/11
 */
public class ContainerWithMostWater {

    //[1,8,6,2,5,4,8,3,7] 49
    private static class Solution {
        public int maxArea(int[] height) {

            if (height == null || height.length < 1) {
                return 0;
            }
            int low = 0;
            int high = height.length - 1;
            int maxArea = 0;

            while (low < high) {
                int head = height[low];
                int tail = height[high];

                int area = 0;
                if (head > tail) {
                    area = tail * (high - low);
                    high--;
                } else {
                    area = head * (high - low);
                    low++;
                }

                if (area > maxArea) {
                    maxArea = area;
                }

            }
            return maxArea;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1,8,6,2,5,4,8,3,7};
        int maxArea = solution.maxArea(arr);
        System.out.println(maxArea);
    }
}
