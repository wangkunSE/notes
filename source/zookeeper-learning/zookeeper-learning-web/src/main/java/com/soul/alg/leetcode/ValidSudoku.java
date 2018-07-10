package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/29
 * @time: 上午11:14
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int length = board.length;
        Map<Character,Integer> rowTemp = new HashMap<>(length);
        Map<Character,Integer> columTemp = new HashMap<>(length);
        //row colum
        for(int i = 0; i<length;i++ ){
            for(int j = 0; j<length;j++){
                //row
                if (checkBoardValid(board, rowTemp, i, j)) return false;
                //colum
                if (checkBoardValid(board, columTemp, j, i)) return false;

            }
            columTemp.clear();
            rowTemp.clear();
        }
        //boxes
        for(int i= 0; i < length; i+=3){
            for(int j = 0; j < length; j+=3){
                boolean flag = checkBoxes(board,i,j);
                if(!flag){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkBoardValid(char[][] board, Map<Character, Integer> tempMap, int i, int j) {
        if(board[i][j] == '.' ){
            tempMap.put('.',0);
        }else if(board[i][j] != '.'){
            if(tempMap.get(board[i][j])!=null){
                return true;
            }else{
                tempMap.put(board[i][j],1);
            }
        }
        return false;
    }

    private boolean checkBoxes(char[][] board,int startI,int startJ){
        Map<Character,Integer> map = new HashMap<>();
        for(int i = startI; i< startI+3; i++){
            for(int j = startJ; j < startJ+3; j++){
                if (checkBoardValid(board, map, i, j)) return false;
            }
        }
        map.clear();
        return true;
    }
}
