package com.soul.alg.leetcode2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2020/8/17
 */
public class MinSizeOfSubArraySum {
    static class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }

            int maxIndex = 0;
            int totalSum = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[maxIndex] < nums[i]) {
                    maxIndex = i;
                }
                totalSum += nums[i];
            }

            if (nums[maxIndex] >= s) {
                return 1;
            }

            if (totalSum < s) {
                return 0;
            }

            int sum = 0;

            int head = maxIndex - 1;
            int tail = maxIndex + 1;
            List<String> subArray = new ArrayList<>();
            sum += nums[maxIndex];
            subArray.add(nums[maxIndex] + "");
            while (head >= 0 && tail < nums.length && sum < s) {
                int curNum = 0;
                if (nums[head] > nums[tail]) {
                    curNum = nums[head--];
                } else {
                    curNum = nums[tail++];
                }
                sum += curNum;
                subArray.add(curNum + "");
            }

            if (sum < s) {
                if (head < 0) {
                    for (int i = tail; i < nums.length && sum < s; i++) {
                        sum += nums[i];
                        subArray.add(nums[i] + "");
                    }
                } else {
                    for (int i = head; i >= 0 && sum < s; i--) {
                        sum += nums[i];
                        subArray.add(nums[i] + "");
                    }
                }
            }
            head++;
            tail--;
            while (tail > head && sum >= s) {
                int tailNum = nums[tail];
                int headNum = nums[head];
                if (tailNum < headNum) {
                    sum -= tailNum;
                    if (sum >= s) {
                        subArray.remove(tail--);
                    }
                } else {
                    sum -= headNum;
                    if (sum >= s) {
                        subArray.remove(head++);
                    }
                }
            }

            return subArray.size();
        }
    }


    static class SolutionII{
        public int minSubArrayLen(int s, int[] nums) {
            if (nums.length ==0) {
                return 0;
            }
            int curSum = nums[0];
            int left = 0, right = 1;
            int result = Integer.MAX_VALUE;
            while(left < nums.length) {
                while(right < nums.length && curSum < s) {
                    //keep adding numbers into curSum
                    curSum+=nums[right];
                    right++;
                }
                if (curSum >= s) {
                    result = Math.min(result, right - left);

                }
                curSum-=nums[left];
                //removing nums[left] from curSum
                left++;

            }
            return result == Integer.MAX_VALUE ? 0 : result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        int subArrayLen = solution.minSubArrayLen(213, arr);
        System.out.println(subArrayLen);
    }
}
