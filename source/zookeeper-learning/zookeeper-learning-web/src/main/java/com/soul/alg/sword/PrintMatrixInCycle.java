package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/18
 */
public class PrintMatrixInCycle {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };
        int column = 5;
        int row = 5;
        new PrintMatrixInCycle().printMatrixInCycle(arr, column, arr.length);
//        System.out.println(arr.length);
    }

    private void printMatrixInCycle(int[][] arr, int column, int row) {
        if (column <= 0 || row <= 0) {
            return;
        }
        int start = 0;
        while (column > start * 2 && row > start * 2) {
            printMatrixOneCycle(arr, column, row, start);
            start++;
        }
    }

    private void printMatrixOneCycle(int[][] arr, int column, int row, int start) {
        int endX = column - start - 1;
        int endY = row - start - 1;

        //打印一行
        for (int i = start; i <= endX; i++) {
            System.out.print(arr[start][i] + "\t");
        }
        System.out.println();

        //打印向下的一列
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(arr[i][endX] + "\t");
            }
        }
        System.out.println();
        //向左打印
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(arr[endY][i] + "\t");
            }
        }
        System.out.println();
        //向上打印一行
        if (start < endX && (start + 1 < endY)) {
            for (int i = endY - 1; i > start; i--) {
                System.out.print(arr[i][start] + "\t");
            }
        }
        System.out.println();
    }
}
