package com.soul.alg.netease;

import java.util.Scanner;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/11
 * @time: 下午3:26
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */

public class Main {


    public static void main(String[] args) {
        Third third = new Third();
        third.printKthEle();

    }

    private static class First {
        public void printMaxHobbit() {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] hobit = new int[n];
            int[] awake = new int[n];

            for (int i = 0; i < n; i++) {
                hobit[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                awake[i] = sc.nextInt();
            }
            int result = 0;

            for (int i = 0; i < n; i++) {
                if (awake[i] == 1) {
                    result += hobit[i];
                    hobit[i] = 0;
                }
            }

            int j = 0;
            int max = 0;
            int temp = 0;
            int count = 0;
            for (int i = 0; j < n; i++) {
                while (j < n && count < k) {
                    temp += hobit[j];
                    j++;
                    count++;
                }
                if (count == k) {
                    max = Math.max(max, temp);
                    temp -= hobit[i++];
                    count--;
                }
            }

            System.out.println(result + max);
        }
    }

    private static class Second {
        public void whichHeap() {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if (n < 1) {
                return;
            }
            int[] apples = new int[n];
            for (int i = 0; i < n; i++) {
                apples[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            if (m < 1) {
                return;
            }
            int[] queries = new int[m];
            for (int i = 0; i < m; i++) {
                queries[i] = sc.nextInt();
            }

            for (int i = 1; i < n; i++) {
                apples[i] += apples[i - 1];
            }

            for (int query : queries) {
                int i = binarySearch(apples, 0, n - 1, query);
                System.out.println(i + 2);
            }
        }

        private int binarySearch(int[] arr, int start, int end, int target) {
            while (start <= end) {
                int mid = (end - start) / 2 + start;
                if (arr[mid] == target) {
                    return mid - 1;
                } else if (arr[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            }
            return end;
        }
    }

    private static class Third {
        public void printKthEle() {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int z = sc.nextInt();
            int k = sc.nextInt();

            if (a < 1 || z < 1) {
                System.out.println(1);
            }
            long limit1 = 1;
            long limit2 = 1;
            for (int i = 1; i <= a; i++) {
                limit1 *= (z + a - i + 1);
                limit2 *= i;
            }
            long n = limit1 / limit2;
            if (k < 1 || k > n) {
                return;
            }

            StringBuilder sb = new StringBuilder();
            if (k == 1) {
                for (int i = 0; i < a; i++) {
                    sb.append("a");
                }
                for (int i = 0; i < z; i++) {
                    sb.append("z");
                }
            } else if (k == n) {
                for (int i = 0; i < z; i++) {
                    sb.append("z");
                }
                for (int i = 0; i < z; i++) {
                    sb.append("a");
                }
            }
            System.out.println(sb.toString());


        }
    }

    public void stackOverFlow(){
        stackOverFlow();
    }
}