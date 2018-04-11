package com.soul.alg.leetcode;

/**
 * @author wangkun1
 * @version 2018/4/9
 */
public class ZigZagString {

    public static void main(String[] args) {
        String str = new ZigZagString().convert("PAHNAPLSIIGYIR", 3);
        System.out.println(str);
    }

    private String convert(String source, int numRows) {

        if (source == null || numRows <= 2 || source.length() <= numRows) {
            return source;
        }
        char[] sourceChars = source.toCharArray();
        int targetLength = sourceChars.length;
        Integer rowNum = 0;
        int temp = 2 * numRows - 2;
        rowNum = (targetLength / temp) * (numRows - 1);
        int mod = targetLength % temp;
        if (mod <= numRows) {
            rowNum += 1;
        } else {
            mod -= numRows;
            rowNum += 1;
            rowNum += mod;
        }
        char[][] targetChars = new char[rowNum][numRows];


        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < numRows; j++) {
                sb.append(targetChars[j][i] == ' ' ? "" : targetChars[j][i]);
            }
        }
        String s = sb.toString();
        return s.replaceAll("\\s*", "");
    }
}
