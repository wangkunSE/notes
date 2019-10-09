package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/8
 */
public class GrayCode_89 {

    public static void main(String[] args) {

        System.out.println(new GrayCode_89().grayCode(2));
    }

    public List<Integer> grayCode(int n) {
        if (n <= 0) {
            return Arrays.asList(0);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < Math.pow(2, n); i++) {
            result.add(i);
        }

        return result;
    }
}
