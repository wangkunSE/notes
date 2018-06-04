package com.soul.alg.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/1
 * @time: 下午6:07
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class LetterCombinations {

    public static void main(String[] args) {
        First first = new First();
        String str = "123344123";
        List<String> strings = first.letterCombinations(str);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    private static class First {
        public List<String> letterCombinations(String digits) {
            LinkedList<String> ans = new LinkedList<String>();
            if(digits.isEmpty()) {
                return ans;
            }
            String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            ans.add("");
            for(int i =0; i<digits.length();i++){
                int x = Character.getNumericValue(digits.charAt(i));
                while(ans.peek().length()==i){
                    String t = ans.remove();
                    for(char s : mapping[x].toCharArray()) {
                        ans.add(t+s);
                    }
                }
            }
            return ans;
        }

    }
}
