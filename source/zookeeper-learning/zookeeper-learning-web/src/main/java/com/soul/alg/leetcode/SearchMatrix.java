package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/8
 * @time: 上午10:59
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SearchMatrix {
    public static void main(String[] args) {
        First first = new First();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int target = 11;
        first.searchMatrix(matrix, target);

    }

    private static class First {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
                return false;
            }
            int rowLength = matrix.length;
            int[] rowArr = new int[rowLength];
            for (int i = 0; i < matrix.length; i++) {
                rowArr[i] = matrix[i][0];
            }

            int row = binarySearch(rowArr, 0, rowLength - 1, target);
            if (matrix[row][0] == target) {
                return true;
            }
            int column = binarySearch(matrix[row], 0, matrix[row].length - 1, target);
            if (matrix[row][column] == target) {
                return true;
            }
            return false;
        }

        private int binarySearch(int[] arr, int start, int end, int target) {
            while (start <= end) {
                int mid = (end - start) / 2 + start;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            }
            return end;
        }
    }
}
