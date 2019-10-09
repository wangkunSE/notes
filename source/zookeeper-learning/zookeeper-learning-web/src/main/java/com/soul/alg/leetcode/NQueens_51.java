package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/8
 */
public class NQueens_51 {
    public static void main(String[] args) {

        System.out.println(new NQueens_51().solveNQueens(8));
    }

    char[][] chessBroad = null;
    private static final char QUEEN = 'Q';
    private static final char DOT = '.';


    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            List<String> lists = new ArrayList<>();
            lists.add("Q");
            return Collections.singletonList(lists);
        }

        List<List<String>> result = new ArrayList<>();

        initChessBroad(n);
        findAllQueens(result, n, 0);
        return result;
    }

    private void initChessBroad(int n) {
        chessBroad = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBroad[i][j] = DOT;
            }
        }
    }

    private void findAllQueens(List<List<String>> result, int limit, int start) {
        if (start >= limit) {
            fillResult(result);
            return;
        }

        for (int j = 0; j < limit; j++) {
            if (couldPutQueen(start, j)) {
                chessBroad[start][j] = QUEEN;
                findAllQueens(result, limit, start + 1);
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


    private void fillResult(List<List<String>> result) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < chessBroad.length; i++) {
            answer.add(new String(chessBroad[i]));
        }
        result.add(answer);
    }
}
