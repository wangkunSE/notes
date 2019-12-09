package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/12/9
 */
public class PascalTriangle_118 {

    public static void main(String[] args) {
        List<List<Integer>> generate = new PascalTriangle_118().generate(10);
        for (List<Integer> list : generate) {
            System.out.println(list);
        }

    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }

        int rowNum = 1;
        result.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> preTemp = result.get(i - 1);
            List<Integer> tempResult = new ArrayList<>();
            for (int j = 0; j <= rowNum; j++) {
                if (j == 0 || j == rowNum) {
                    tempResult.add(1);
                } else {
                    tempResult.add(preTemp.get(j - 1) + preTemp.get(j));
                }
            }
            result.add(tempResult);
            rowNum++;
        }

        return result;
    }
}
