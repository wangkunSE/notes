package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/24
 * @time: 上午10:43
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class NQueen {


    public static void main(String[] args) {
        First first = new First();
        List<List<String>> lists = first.solveNQueens(4);
        System.out.println(lists);
    }

    private static class First {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            if (n < 4) {
                return result;
            }
            char[][] chess = new char[n][n];
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    chess[i][j] = '.';
                }
            }
            backtrace(result, n, 0, chess);
            return result;
        }

        private boolean backtrace(List<List<String>> list, int queenCount, int row, char[][] chess) {
            if (queenCount == 0) {
                List<String> solution = covertSolution(chess);
                list.add(solution);
                return false;
            } else {
                for (int i = row; i < chess.length; i++) {
                    for (int j = 0; j < chess.length; j++) {
                        if (isValid(chess, i, j)) {
                            chess[i][j] = 'Q';
                            if (backtrace(list, queenCount - 1, i + 1, chess)) {
                                return true;
                            } else {
                                chess[i][j] = '.';
                            }
                        }
                    }
                }
            }
            return false;
        }

        private boolean isValid(char[][] chess, int row, int colum) {
            //column
            for (int i = 0; i < row; i++) {
                if (chess[i][colum] == 'Q') {
                    return false;
                }
            }

            //diagonal
            for (int i = row - 1; i >= 0; i--) {
                int i3 = colum - (row - i);
                if (i3 >= 0 && chess[i][i3] == 'Q') {
                    return false;
                }
                int i2 = colum + row - i;
                if (i2 < chess.length && chess[i][i2] == 'Q') {
                    return false;
                }
            }

            return true;
        }

        private List<String> covertSolution(char[][] chess) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < chess.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < chess.length; j++) {
                    sb.append(chess[i][j]);
                }
                result.add(sb.toString());
            }
            return result;
        }
    }
}
