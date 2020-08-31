package com.soul.alg.leetcode2.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/8/24
 */
public class IntersectionOfTwoArray {
    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null
                    || nums1.length < 1 || nums2.length < 1) {
                return new int[]{};
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums1) {
                Integer value = map.get(num);
                if (Objects.nonNull(value)) {
                    map.put(num, ++value);
                } else {
                    map.put(num, 1);
                }
            }

            List<Integer> result = new ArrayList<>();

            for (int num : nums2) {
                Integer value = map.get(num);
                if (Objects.nonNull(value)) {
                    if (value > 0) {
                        result.add(num);
                        map.put(num, --value);
                    }
                }
            }

            if (result.size() > 0) {
                int[] resultArr = new int[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    resultArr[i] = result.get(i);
                }
                return resultArr;
            }
            return new int[]{};
        }
    }

    static class SolutionII {
        public int[] intersection(int[] nums1, int[] nums2) {
            int[] map = new int[1001];
            for (int i : nums1) {
                if (map[i] == 0) map[i]++;
            }

            int[] res = new int[0];

            for (int i : nums2) {
                if (map[i] == 1) {
                    res = append(res, i);
                    map[i]--;
                }
            }

            return res;
        }

        private int[] append(int[] arr, int num) {
            int n = arr.length;
            int[] res = new int[n + 1];
            System.arraycopy(arr, 0, res, 0, n);
            res[n] = num;

            return res;
        }
    }
}
