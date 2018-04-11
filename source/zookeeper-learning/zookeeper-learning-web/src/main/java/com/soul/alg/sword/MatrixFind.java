package com.soul.alg.sword;

import java.util.Scanner;

/**
 * @author wangkun1
 * @version 2018/4/11
 */
public class MatrixFind {

    public static void main(String[] args) {
        new MatrixFind().find();
    }

    private void find() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 19, 23, 43}};
        int target = 9;
        System.out.println(findTheTarget(matrix, 3, 4, target));
    }

    private boolean findTheTarget(int[][] matrix, int row, int colum, int target) {
        if (matrix == null || matrix.length <= 0) {
            return false;
        }
        int i = 0;
        int j = colum - 1;
        while (i < row && j >= 0) {
            int temp = matrix[i][j];
            if (temp == target) {
                return true;
            } else if (temp > target && j > 0) {
                j--;
            } else if (temp < target && i < row - 1) {
                i++;
            } else {
                return false;
            }
        }
        return false;
    }
}
