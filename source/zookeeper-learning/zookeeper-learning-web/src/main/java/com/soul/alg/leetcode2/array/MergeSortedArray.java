package com.soul.alg.leetcode2.array;

import com.soul.alg.leetcode2.AbstractAlg;
import java.util.Arrays;

/**
 * @author wangkunwk
 * @version 2020/8/5
 */
public class MergeSortedArray extends AbstractAlg {

    private static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (nums1 == null || nums2 == null) {
                return;
            }
            int[] nums3 = Arrays.copyOf(nums1, m);

            int k = 0;
            int l = 0;
            int i = 0;
            for ( i = 0; i < m + n && k < m && l < n; i++) {
                if (nums3[k] > nums2[l]) {
                    nums1[i] = nums2[l++];
                } else {
                    nums1[i] = nums3[k++];
                }
            }

           while (k<m){
               nums1[i++] = nums3[k++];
           }
           while (l<n){
               nums1[i++] = nums2[l++];
           }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        solution.merge(nums1, 3, nums2, 3);
        printArr(nums1);
    }
}
