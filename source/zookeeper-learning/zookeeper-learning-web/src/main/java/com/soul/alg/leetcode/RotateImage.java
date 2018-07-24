package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/23
 * @time: 下午3:29
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class RotateImage {
    public static void main(String[] args) {
//        First first = new First();
//        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        first.rotate(matrix);
//
//        Map<Integer,List<String>> map = new HashMap<>();
        double pow = Math.pow(1.1, Integer.MAX_VALUE/2);
        System.out.println(pow);

    }

    private static class First {
        public void rotate(int[][] matrix) {

            if( matrix == null || matrix.length < 1){
                return;
            }

            int rowLength = matrix.length;
            int colLength = matrix[0].length;
            for(int i = 0; i < rowLength - 1;i++ ){
                for(int j = 0; j < colLength - i -1; j++){
                    swap(matrix,i,j,colLength-j-1,rowLength-i-1);
                }
            }

            for(int i = 0; i < rowLength/2; i++){
                for(int j = 0; j < colLength; j++){
                    swap(matrix,i,j,rowLength-i-1,j);
                }
            }
        }

        private void swap(int[][] nums,int fromX,int fromY,int toX,int toY){
            int temp = nums[fromX][fromY];
            nums[fromX][fromY] = nums[toX][toY];
            nums[toX][toY] = temp;
        }
    }
}
