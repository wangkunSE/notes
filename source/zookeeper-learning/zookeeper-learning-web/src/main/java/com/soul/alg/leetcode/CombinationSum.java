package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/4
 * @time: 上午10:34
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class CombinationSum {
    public static void main(String[] args) {
        First first = new First();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = first.combinationSum(candidates, target);
        System.out.println(lists);
    }

    private static class First {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Set<List<Integer>> set = new HashSet<>();
            if (candidates == null || candidates.length < 1) {
                return result;
            }
            Arrays.sort(candidates);
            int length = candidates.length;
            getAllIndex(set, candidates, length, target);
            result.addAll(set);
            return result;

        }

        private boolean getAllIndex(Set<List<Integer>> set, int[] candidates, int length, int target) {
            for (int i = 0; i < length; i++) {
                boolean flag = true;
                int temp = candidates[i];
                if (temp == 0 || temp > target) {
                    continue;
                }
                int mod = target % temp;
                List<Integer> list = new ArrayList<>();
                int count = target / temp;
                while (count > 0) {
                    list.add(temp);
                    count--;
                }
                if (mod != 0) {
                    int index = binarySearch(candidates, mod);
                    if (index != -1) {
                        list.add(candidates[index]);
                    } else if (mod < candidates[0]) {
                        getAllIndex(set,candidates,length,target-temp);
                        flag = false;
                    } else {
                        flag = getAllIndex(set, candidates, length, mod);
                    }
                }
                if (flag) {
                    set.add(list);
                }
            }
            return true;
        }

        private int binarySearch(int[] arr, int target) {
            int low = 0, high = arr.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }
    }
}
