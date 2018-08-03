package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/26
 * @time: 上午11:23
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class GetPermutation {
    public static void main(String[] args) {
//        First first = new First();
        Second first = new Second();
        String permutation = first.getPermutation(3, 3);
        System.out.println(permutation);
    }

    private static class First {
        int hasDone = 0;

        public String getPermutation(int n, int k) {
            int fac = factorial(n - 1);
            int start = k < fac ? 0 : k / fac;
            List<List<Integer>> result = new ArrayList<>();
            int[] nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i + 1;
            }
            if (start >= nums.length) {
                start = nums.length - 1;
            }else if (start < 0){
                start = 0;
            }
            int temp = nums[start];
            for (int i = start; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = temp;
            hasDone += fac * start;
            getAllCombination(result, nums, new ArrayList<>(), k);
            if (result.size() == 0){
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    list.add(nums[i]);
                }
                result.add(list);
            }
            List<Integer> target = result.get(result.size() - 1);
            return covertToString(target);
        }

        private int factorial(int n) {
            if (n == 0) {
                return 1;
            }
            return n * factorial(n - 1);
        }

        private String covertToString(List<Integer> list) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : list) {
                sb.append(i + "");
            }
            return sb.toString();
        }

        private void getAllCombination(List<List<Integer>> list, int[] nums, List<Integer> temp, int k) {
            if (hasDone == k) {
                return;
            }
            if (temp.size() == nums.length) {
                hasDone++;
                list.add(new ArrayList<>(temp));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (temp.contains(nums[i])) {
                        continue;
                    }
                    temp.add(nums[i]);
                    getAllCombination(list, nums, temp, k);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    private static class Second {
        public String getPermutation(int n, int k) {
            int pos = 0;
            List<Integer> numbers = new ArrayList<>();
            int[] factorial = new int[n+1];
            StringBuilder sb = new StringBuilder();

            // create an array of factorial lookup
            int sum = 1;
            factorial[0] = 1;
            for(int i=1; i<=n; i++){
                sum *= i;
                factorial[i] = sum;
            }
            // factorial[] = {1, 1, 2, 6, 24, ... n!}

            // create a list of numbers to get indices
            for(int i=1; i<=n; i++){
                numbers.add(i);
            }
            // numbers = {1, 2, 3, 4}

            k--;

            for(int i = 1; i <= n; i++){
                int index = k/factorial[n-i];
                sb.append(String.valueOf(numbers.get(index)));
                numbers.remove(index);
                k-=index*factorial[n-i];
            }

            return String.valueOf(sb);
        }
    }
}
