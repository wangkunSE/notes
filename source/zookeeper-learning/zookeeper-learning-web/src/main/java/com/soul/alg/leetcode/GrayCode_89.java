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
        List<Integer> rs = new ArrayList<Integer>();
        rs.add(0);
        for (int i = 0; i < n; i++) {
            int size = rs.size();
            for (int k = size - 1; k >= 0; k--) {
                int moveLeft = 1 << i;
                int curNum = rs.get(k) | moveLeft;
                rs.add(curNum);
            }
        }
        return rs;
    }
}
