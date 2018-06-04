package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/4
 * @time: 上午10:04
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Parenthess {

    public static void main(String[] args) {
        int n = 20;
        List<String> strings = new First().generateParenthesis(n);
        System.out.println(strings);
    }

    private static class First {
        public List<String> generateParenthesis(int n) {
            if( n <= 0){
                return new ArrayList<>();
            }
            List<List<Integer>> resultTypes = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            list.add(-1);
            resultTypes.add(list);
            for( int i = 1; i < 2*n; i++ ){
                ListIterator<List<Integer>> listListIterator = resultTypes.listIterator();
                while (listListIterator.hasNext()){
                    List<Integer> tempList = listListIterator.next();
                    Integer sum = tempList.stream().reduce((x,y)->x+y).get();
                    if(sum == 0){
                        tempList.add(-1);
                    }else{
                        List<Integer> newList = new ArrayList<>();
                        newList.addAll(tempList);
                        tempList.add(-1);
                        newList.add(1);
                        listListIterator.add(newList);
                    }
                }
            }
            ListIterator<List<Integer>> listListIterator = resultTypes.listIterator();
            while (listListIterator.hasNext()){
                List<Integer> tempList = listListIterator.next();
                Integer sum = tempList.stream().reduce((x,y)->x+y).get();
                if(sum < 0){
                 listListIterator.remove();
                }
            }
            List<String> resp = new ArrayList<>();
            for(List<Integer> intList : resultTypes){
                StringBuilder sb = new StringBuilder();
                for(Integer tempInt: intList){
                    if(tempInt == -1){
                        sb.append("(");
                    }else{
                        sb.append(")");
                    }
                }
                resp.add(sb.toString());
            }

            return resp;
        }
    }
}
