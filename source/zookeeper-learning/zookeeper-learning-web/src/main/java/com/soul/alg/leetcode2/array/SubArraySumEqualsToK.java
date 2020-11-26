package com.soul.alg.leetcode2.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given an array of integers nums and an integer k,
 * return the total number of continuous subarrays
 * whose sum equals to k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * @author wangkunwk
 * @version 2020/11/12
 */
public class SubArraySumEqualsToK {

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            if (Objects.isNull(nums)) {
                return 0;
            }

            int result = 0;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum == k) {
                    result += 1;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        result += 1;
                    }
                }
                sum = 0;
            }

            return result;
        }
    }


    static class SolutionII {
        class Solution {
            /*
                Number of continuous subarrays: Can be found in O(n^2)
                Brute force: nested loop


                Sliding window?
                While running sum is < k, advance right pointer,
                If left pointer = right pointer, advance right pointer
                If running sum > k, advance left pointer
                Sliding window approach only works if input is sorted, since we are counting continuous subarrays, sliding window doesn't work here


               What if we compute the cumulative sum c(i), then sum(i,j), "sum from i to j, inclusive" = c(j)-c(i-1)
               We want:
                All i,j pairs such that sum(i,j) = k = c(j)-c(i-1)
                Therefore, precompute and store c(j) - k, then iterate over nums, if any c(i-1) exists in the hashset, increment counter
                Or precompute and store k + c(i-1), then iterate over nums and check if any c(j) exists in the hashset
                Identity:
                    c(-1) = 0

               [1 2 3 4] k=3
               nums[j]   c(j)         c(j)-k  j   c(i-1)  k+c(i-1)
                             0               -1
                1         1= 1,       -2      0    0      3
                  2       1+2= 3,      0      1    1      4
                    3     1+2+3= 6,    3      2    3      6
                      4   1+2+3+4= 10  7      3    6      9



        [1,1,1]
        2
        [1]
        0
        [-1,-1,1]
        0
        [28,54,7,-70,22,65,-6]
        100
        [1,2,3,4,5,6,7,1,23,21,3,1,2,1,1,1,1,1,12,2,3,2,3,2,2]
        42
        [0,0,0,0,0,0,0,0,0,0]
        0
            */
            // /*
            public int subarraySum(int[] nums, int k) {
                Map<Integer, Integer> diffCount = new HashMap<>(nums.length);
                int sum = 0, count = 0;
                for (int i = 0; i < nums.length; i++) {
                    diffCount.merge(k + sum, 1, (old, val) -> old + val);
                    sum += nums[i];
                    if (diffCount.containsKey(sum)) {
                        count += diffCount.get(sum);
                    }
                }
                return count;
            }
        }

    }

    //Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2),
    // Space complexity O(1). I bet this solution will TLE.
    //
    //Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j].
    // So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j].
    // To achieve this, we just need to go through the array, calculate the current sum and save number of
    // all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).

    //


    // Sliding window -- No, contains negative number
    // hashmap + preSum
        /*
            1. Hashmap<sum[0,i - 1], frequency>
            2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
                   k           sum      hashmap-key     -->  hashmap-key  =  sum - k
            3. now, we have k and sum.
                  As long as we can find a sum[0, i - 1], we then get a valid subarray
                 which is as long as we have the hashmap-key,  we then get a valid subarray
            4. Why don't map.put(sum[0, i - 1], 1) every time ?
                  if all numbers are positive, this is fine
                  if there exists negative number, there could be preSum frequency > 1
        */


    //any idea guys, why do we have to initialize map like preSum.put(0, 1); .........
    // I mean how would we know to do this if approaching this solution for first time?
    // I got most of the things in for loop correct (ofc i had seen "two sum" problem, "Maximum Size
    // Subarray Sum Equals k" under amazon problem before and used similar ideas here...)...
    // still don't get why we initialize the map...
    //
    //##################
    //
    //I see ...After spending some time on the analysis,
    // I found the reason behind having initialize preSum.put(0,1)....
    // it is for those (sum - k) == 0 calculations which are valid subarrays
    // but need to get counted. e.g.
    // if k = 7 and sum = 7 (at second element for array is : 3, 4, 3, 8)
    // at some iteration.....then sum - k = 0....
    // this 0 will get counted in
    // statement result += preSum.get(sum - k);
    //
    //#############
    //
    //So in conclusion, the initial entry preSum.put(0, 1) can be exchanged with statement :
    //if (sum == k) count++;
    //we can put it just below sum += nums[i];
    // statement. This will make code more intuitive...
    // as we are trying to find the sum which matches to k!

    static class SolutionIII {
        public int subarraySum(int[] nums, int k) {
            int sum = 0, result = 0;
            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum - k)) {
                    result += preSum.get(sum - k);
                }
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
            }

            return result;
        }
    }


    static class SolutionIV {
        public int subarraySum(int[] nums, int k) {
            if (Objects.isNull(nums)) {
                return 0;
            }

            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);

            int sum = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum - k)) {
                    count += preSum.get(sum - k);
                }
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
            }

            return count;

        }
    }

    static class SolutionV {
        public int subarraySum(int[] nums, int k) {
            final int[] sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
            return mergeSort(sums, 0, nums.length, k, new int[nums.length / 2 + 1]);
        }

        //[st, ed]
        private int mergeSort(int[] sums, int st, int ed, int k, int[] paper) {
            if (st == ed) return 0;
            int mid = (st + ed) / 2;
            int ans = mergeSort(sums, st, mid, k, paper) + mergeSort(sums, mid + 1, ed, k, paper);
            for (int i = st, j = mid + 1; i <= mid && j <= ed; i++) {
                while (j <= ed && sums[j] < k + sums[i]) j++;
                if (j <= ed && sums[j] == k + sums[i]) {
                    int t = j + 1;
                    while (t <= ed && sums[t] == sums[j]) t++;
                    ans += t - j;
                }
            }
            int len = mid - st + 1;
            System.arraycopy(sums, st, paper, 0, len);
            int p = 0, i = st;
            for (int q = mid + 1; p < len && q <= ed; ) {
                sums[i++] = paper[p] <= sums[q] ? paper[p++] : sums[q++];
            }
            while (p < len) sums[i++] = paper[p++];
            return ans;
        }
    }

    public static void main(String[] args) {
        SolutionIII solutionIII = new SolutionIII();
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println(solutionIII.subarraySum(arr, 3));
    }

}
