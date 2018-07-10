package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/10
 * @time: 上午11:40
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        First first = new First();
        int[] A = {1,2};
        int[] B = {3,4};
        double medianSortedArrays = first.findMedianSortedArrays(A, B);
        System.out.println(medianSortedArrays);
    }

    private static class First {
        public double findMedianSortedArrays(int[] A, int[] B) {
            int[] longArr = null;
            int[] shortArr = null;
            if( A.length <= B.length){
                longArr = B;
                shortArr = A;
            }else{
                longArr = A;
                shortArr = B;
            }

            int m = shortArr.length,n = longArr.length;
            if(n < 1){
                return 0;
            }
            int imin = 0,imax = m,halfLen = (m+n+1)/2;
            while(imin <= imax ){
                int i = (imin + imax)/2;
                int j = halfLen - i;
                if(i>0 && j < n && shortArr[i-1] > longArr[j] ){
                    imin = i - 1;
                }else if( j > 0 && i < m && longArr[j-1] > shortArr[i]){
                    imin = i + 1;
                }else{
                    double leftMax = 0.0;
                    if(i == 0){
                        leftMax = longArr[j-1];
                    }else if(j == 0){
                        leftMax = shortArr[i-1];
                    }else{
                        leftMax = Math.max(shortArr[i-1],longArr[j-1]) * 1.0;
                    }
                    if( (m + n) % 2 == 1){
                        return leftMax * 1.0;
                    }

                    double rightMin = 0.0;
                    if(i == m){
                        rightMin = longArr[j];
                    }else if(j == n){
                        rightMin = shortArr[i];
                    }else{
                        rightMin = Math.min(shortArr[i],longArr[j]) * 1.0 ;
                    }
                    return  (leftMax + rightMin)/2;
                }
            }
            return 0.0;
        }
    }
}
