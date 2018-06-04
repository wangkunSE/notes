package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/5/30
 * @time: 下午3:42
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class FourSum {

    public static void main(String[] args) {
        First first = new First();
        int[] num = {-1, 0, 1, 2, -1, -4};
        int target = -1;
        List<List<Integer>> lists = first.fourSum(num, target);
        System.out.println(lists);
    }

    private static class First {
        public List<List<Integer>> fourSum(int[] num, int target) {
            List<List<Integer>> res = new LinkedList<>();
            if (num == null || num.length < 5) {
                List list = new ArrayList();
                if (num.length < 4) {
                    return list;
                }
                int sum = 0;
                for (int n : num) {
                    sum += n;
                    list.add(n);
                }
                if (sum == target) {
                    res.add(list);
                }
                return res;
            }
            Arrays.sort(num);

            for (int j = 0; j < num.length - 2; j++) {
                for (int i = j + 1; i < num.length - 2; i++) {
                    int lo = i + 1, hi = num.length - 1, sum = target - num[i] - num[j];
                    while (lo < hi) {
                        if (num[lo] + num[hi] == sum) {
                            ((LinkedList<List<Integer>>) res).remove(Arrays.asList(num[j], num[i], num[lo], num[hi]));
                            res.add(Arrays.asList(num[j], num[i], num[lo], num[hi]));
                            while (lo < hi && num[lo] == num[lo + 1]) {
                                lo++;
                            }
                            while (lo < hi && num[hi] == num[hi - 1]) {
                                hi--;
                            }
                            lo++;
                            hi--;
                        } else if (num[lo] + num[hi] < sum) {
                            lo++;
                        } else {
                            hi--;
                        }
                    }
                }
            }
            return res;
        }
    }
}
