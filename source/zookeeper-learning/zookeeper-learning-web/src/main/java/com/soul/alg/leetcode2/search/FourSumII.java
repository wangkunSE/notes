package com.soul.alg.leetcode2.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/9/12
 */
public class FourSumII {

    static class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            if (Objects.isNull(A) || Objects.isNull(B) || Objects.isNull(C) || Objects.isNull(D)) {
                return 0;
            }

            Map<Integer, Integer> abSumIndex = new HashMap<>();
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    int sum = A[i] + B[j];
                    if (abSumIndex.containsKey(-sum)) {
                        abSumIndex.put(-sum, abSumIndex.get(-sum) + 1);
                    } else {
                        abSumIndex.put(-sum, 1);
                    }
                }
            }

            Map<Integer, Integer> cdSumIndex = new HashMap<>();
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < D.length; j++) {
                    int sum = C[i] + D[j];
                    if (cdSumIndex.containsKey(sum)) {
                        cdSumIndex.put(sum, cdSumIndex.get(sum) + 1);
                    } else {
                        cdSumIndex.put(sum, 1);
                    }
                }
            }
            int result = 0;

            for (Map.Entry<Integer, Integer> abEntry : abSumIndex.entrySet()) {
                Integer cdSum = abEntry.getKey();
                Integer cdSumCnt = abEntry.getValue();

                Integer cdResult = cdSumIndex.get(cdSum);
                if (Objects.nonNull(cdResult)) {
                    result += cdSumCnt * cdResult;
                }
            }



            return result;
        }
    }


}
