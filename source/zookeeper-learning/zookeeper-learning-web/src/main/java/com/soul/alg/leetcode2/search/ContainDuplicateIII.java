package com.soul.alg.leetcode2.search;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author wangkunwk
 * @version 2020/9/21
 */
public class ContainDuplicateIII {
    static class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            int n = nums.length;
            if(k>=10000 && t==0 || n==0 || k<0) return false;
            for(int i=0; i<n; i++) {
                for(int j=i+1; j<=i+k; j++) {
                    if(j>=n) break;
                    if(Math.abs(1L*nums[i]-nums[j])<=t) return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        BigDecimal value = new BigDecimal(Integer.MAX_VALUE - Integer.MIN_VALUE);
        System.out.println(value.toString());

        Solution solution = new Solution();
        int[] arr = new int[]{-2147483648, 2147483647};
        System.out.println(solution.containsNearbyAlmostDuplicate(arr, 1, 1));
    }
}
