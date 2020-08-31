package com.soul.alg.leetcode2.array;

/**
 * @author wangkunwk
 * @version 2020/8/6
 */
public class TwoSumII extends AbstractAlg {

    private static class Solution {
        public int[] twoSum(int[] numbers, int target) {

            int[] result = new int[2];
            if (numbers == null || numbers.length < 1) {
                return result;
            }

            for (int i = 0; i < numbers.length; i++) {

                int index = myBinarySearch(i+1, numbers, target - numbers[i]);
                if (index != -1) {
                    result[0] = i+1;
                    result[1] = index+1;
                    return result;
                }
            }

            return result;
        }

        private int myBinarySearch(int start, int[] numbers, int target) {

            int end = numbers.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (numbers[mid] == target) {
                    return mid;
                } else if (numbers[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            return -1;
        }
    }

    private static class SolutionII{
        public int[] twoSum(int[] numbers, int target) {
            int i = 0;
            int j = numbers.length-1;

            while (i<j){
                int tmp = numbers[i]+numbers[j];
                if (tmp>target){
                    j--;
                }else if (tmp<target){
                    i++;
                }else {
                    return new int[]{i+1,j+1};
                }
            }
            return new int[2];
        }
    }

    public static void main(String[] args) {

        SolutionII solution = new SolutionII();
        int[] arr = new int[]{1,2,3,4,4,9,56,90};
        int[] ints = solution.twoSum(arr, 8);
        printArr(ints);
    }

}
