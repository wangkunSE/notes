package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/9/27
 */
public class GenerateParenthese_23 {

    public static void main(String[] args) {

        System.out.println(new GenerateParenthese_23().generateParenthesis(4));
    }

    public List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int charLength = 2 * n;
        fillParentheseResult(result, n, n, new char[charLength], 0);
        return result;
    }

    private void fillParentheseResult(List<String> result, int left, int right, char[] temp, int offset) {
        if (right == 0) {
            result.add(new String(temp));
            return;
        }

        if (left > 0) {

            temp[offset] = '(';
            fillParentheseResult(result, left - 1, right, temp, offset + 1);
        }

        if (left < right) {

            temp[offset] = ')';
            fillParentheseResult(result, left, right - 1, temp, offset + 1);
        }

    }
}
