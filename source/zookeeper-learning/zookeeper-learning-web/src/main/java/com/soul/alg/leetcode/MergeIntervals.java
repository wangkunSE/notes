package com.soul.alg.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/24
 * @time: 下午2:31
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MergeIntervals {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        First first = new First();
//        ArrayList<Interval> intervals = Lists.newArrayList(new Interval(2, 3), new Interval(4, 5), new Interval(6, 7), new Interval(8, 9), new Interval(1, 10));
        ArrayList<Interval> intervals = Lists.newArrayList(new Interval(1, 4), new Interval(0, 2), new Interval(3, 5));
        List<Interval> merge = first.merge(intervals);
        System.out.println(merge);
    }

    private static class First {
        public List<Interval> merge(List<Interval> intervals) {

            List<Interval> result = new ArrayList<>();
            if (intervals == null || intervals.size() < 1) {
                return result;
            }

            Object[] intervalArr = intervals.toArray();
            Arrays.sort(intervalArr, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    Interval interval1 = (Interval) o1;
                    Interval interval2 = (Interval) o2;
                    return Integer.compare(interval1.start, interval2.start);
                }
            });
            for (int i = 1; i < intervalArr.length; i++) {
                Interval ibefore = (Interval) intervalArr[i - 1];
                Interval intervalI = (Interval) intervalArr[i];
                if (intervalI.start <= ibefore.end) {
                    Interval newInter = new Interval(ibefore.start, intervalI.end > ibefore.end ? intervalI.end : ibefore.end);
                    intervalArr[i] = newInter;
                    addInterval(result, newInter);
                } else {
                    addInterval(result, ibefore);
                }
            }
            addInterval(result, (Interval) intervalArr[intervalArr.length - 1]);
            return result;
        }

        private void addInterval(List<Interval> result, Interval interval) {
            Interval intervalTemp = null;
            if (result.size() > 0) {
                intervalTemp = result.get(result.size() - 1);
                if (intervalTemp.start == interval.start && intervalTemp.end < interval.end) {
                    result.remove(result.size() - 1);
                }
            }
            if (intervalTemp == null || intervalTemp.start != interval.start || intervalTemp.end != interval.end) {
                result.add(interval);
            }
        }
    }
}
