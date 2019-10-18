package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkunwk
 * @version 2019/10/16
 */
public class WorldSearch_79 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        char[][] board2 = new char[][]{{'a'}};
        System.out.println(new WorldSearch_79().exist(board2, "a"));
    }


    public boolean exist(char[][] board, String word) {

        if (board == null || word == null) {
            return false;
        }

        char[] chars = word.toCharArray();
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (chars[0] == board[i][j]) {
                    boolean exist = findExist(i, j, chars, board, 0, used);
                    if (exist) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    private boolean findExist(int row, int column, char[] chars, char[][] board, int offset, boolean[][] used) {
        if (offset >= chars.length) {
            return true;
        }

        if (row >= board.length || column >= board[0].length || row < 0 || column < 0 || used[row][column]) {
            return false;
        }


        if (chars[offset] != board[row][column]) {
            return false;
        }

        used[row][column] = true;
        boolean left = findExist(row, column - 1, chars, board, offset + 1, used);
        boolean top = findExist(row + 1, column, chars, board, offset + 1, used);
        boolean right = findExist(row, column + 1, chars, board, offset + 1, used);
        boolean down = findExist(row - 1, column, chars, board, offset + 1, used);
        used[row][column] = false;

        return right || down || top || left;


    }
}
