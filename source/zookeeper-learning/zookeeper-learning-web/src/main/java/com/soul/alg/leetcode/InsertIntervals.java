package com.soul.alg.leetcode;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/25
 * @time: 下午7:45
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class InsertIntervals {

    public static void main(String[] args) {
        First first = new First();
        ArrayList<MergeIntervals.Interval> intervals = Lists.newArrayList(new MergeIntervals.Interval(1, 3), new MergeIntervals.Interval(6, 9));
        List<MergeIntervals.Interval> insert = first.insert(intervals, new MergeIntervals.Interval(3, 5));
        System.out.println(insert);
    }

    private static class First {
        public List<MergeIntervals.Interval> insert(List<MergeIntervals.Interval> intervals, MergeIntervals.Interval newInterval) {
            if (intervals == null || newInterval == null) {
                return intervals;
            }
            intervals.add(newInterval);
            int length = intervals.size();
            int[] start = new int[length];
            int[] end = new int[length];
            for (int i = 0; i < length; i++) {
                start[i] = intervals.get(i).start;
                end[i] = intervals.get(i).end;
            }

            insertToIndex(start, binarySearch(start, 0, length - 2, start[length - 1]), start[length - 1]);
            insertToIndex(end, binarySearch(end, 0, length - 2, end[length - 1]), end[length - 1]);
            // Arrays.sort(end);
            List<MergeIntervals.Interval> result = new ArrayList<>();
            for (int i = 0, j = 0; i < length; i++) {
                if (i == length - 1 || start[i + 1] > end[i]) {
                    result.add(new MergeIntervals.Interval(start[j], end[i]));
                    j = i + 1;
                }
            }
            return result;
        }

        private int binarySearch(int[] nums, int start, int end, int target) {
            int low = start, high = end;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        private void insertToIndex(int[] nums, int index, int target) {
            for (int i = nums.length - 1; i > index; i--) {
                nums[i] = nums[i - 1];
            }
            nums[index] = target;
        }
    }
}
