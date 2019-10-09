package com.soul.alg.leetcode;

/**
 * @author wangkunwk
 * @version 2019/10/8
 */
public class NQueens_II_52 {

    public static void main(String[] args) {

        System.out.println(new NQueens_II_52().totalNQueens(4));
    }

    int[][] chessBroad = null;
    private static final char QUEEN = 1;
    private static final char DOT = 0;

    int count = 0;


    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        chessBroad = new int[n][n];
        findAllQueens(n, 0);

        return count;
    }

    private void findAllQueens(int limit, int start) {
        if (start >= limit) {
            count++;
            return;
        }

        for (int j = 0; j < limit; j++) {
            if (couldPutQueen(start, j)) {
                chessBroad[start][j] = QUEEN;
                findAllQueens(limit, start + 1);
                chessBroad[start][j] = DOT;
            }
        }
    }

    private boolean couldPutQueen(int i, int j) {
        return validRowAndColumn(i, j) && validCross(i, j);
    }

    private boolean validRowAndColumn(int i, int j) {
        for (int k = 0; k < chessBroad.length; k++) {
            if (chessBroad[i][k] == QUEEN || chessBroad[k][j] == QUEEN) {
                return false;
            }
        }
        return true;
    }

    private boolean validCross(int i, int j) {
        for (int m = i, n = j, l = j; (m >= 0 && n < chessBroad.length) || (m >= 0 && l >= 0); m--, n++, l--) {
            if (n < chessBroad.length) {
                if (chessBroad[m][n] == QUEEN) {
                    return false;
                }
            }

            if (l >= 0) {
                if (chessBroad[m][l] == QUEEN) {
                    return false;
                }
            }

        }
        return true;
    }

}
