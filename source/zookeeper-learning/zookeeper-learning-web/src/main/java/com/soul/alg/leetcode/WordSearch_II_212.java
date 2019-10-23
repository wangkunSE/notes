package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/22
 */
public class WordSearch_II_212 {

    public static void main(String[] args) {
//        testFindWords();
        System.out.println(10142909L/1024/1024);
    }

    private static void testFindWords() {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain", "oathf"};
        System.out.println(new WordSearch_II_212().findWords(board, words));
    }

    public List<String> findWords(char[][] board, String[] words) {

        if (null == board || null == words) {
            return new ArrayList<>();
        }

        TireNode root = buildTireNodes(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findAllResults(result, i, j, root.next[board[i][j] - 'a'], board);
            }
        }

        return result;
    }

    private void findAllResults(List<String> result, int row, int column, TireNode root, char[][] board) {
        char curChar = board[row][column];
        if ('#' == curChar || root == null) {
            return;
        }
        if (null != root.word) {
            result.add(root.word);
            root.word = null;
        }

        board[row][column] = '#';
        if (row > 0 && board[row - 1][column] != '#') {
            findAllResults(result, row - 1, column, root.next[board[row - 1][column] - 'a'], board);
        }

        if (column > 0 && board[row][column - 1] != '#') {
            findAllResults(result, row, column - 1, root.next[board[row][column - 1] - 'a'], board);
        }

        if (row < board.length - 1 && board[row + 1][column] != '#') {
            findAllResults(result, row + 1, column, root.next[board[row + 1][column] - 'a'], board);
        }

        if (column < board[0].length - 1 && board[row][column + 1] != '#') {
            findAllResults(result, row, column + 1, root.next[board[row][column + 1] - 'a'], board);
        }

        board[row][column] = curChar;
    }

    private TireNode buildTireNodes(String[] words) {
        TireNode root = new TireNode();
        for (String word : words) {
            TireNode p = root;
            for (char curChar : word.toCharArray()) {
                int index = curChar - 'a';
                if (p.next[index] == null) {
                    p.next[index] = new TireNode();
                }
                p = p.next[index];
            }
            p.word = word;
        }

        return root;
    }


    static class TireNode {
        public TireNode[] next = new TireNode[26];
        public String word;
    }
}
