package com.soul.alg.sword;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkun1
 * @version 2018/5/7
 */
public class MatrixTrace {

    public static void main(String[] args) {
        char[][] map = {{'a', 'b', 'c'}, {'b', 'e', 'f'}, {'g', 'h', 'i'}};
        String trace = "befih";
        boolean flag = new First().existTrace(map, trace, 3, 3);
        System.out.println(flag);
    }

    private static class First {
        public boolean existTrace(char[][] map, String trace, int row, int column) {

            if (map == null || row <= 0 || column <= 0) {
                return false;
            }
            if (trace == null || trace.length() < 1) {
                return true;
            }
            Map<String, String> flag = new HashMap<>();
            char[] traceChars = trace.toCharArray();
            String[] route = new String[traceChars.length];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (traceChars[0] == map[i][j]) {
                        route[0] = i + "" + j;
                        flag.put(route[0], "");
                        int tempi = i;
                        int tempj = j;
                        boolean tempFlag = true;
                        for (int i1 = 1; i1 < traceChars.length; ) {
                            if (tempi - 1 >= 0 && flag.get((tempi - 1) + "" + tempj) == null && map[tempi - 1][tempj] == traceChars[i1]) {
                                //上
                                tempi = tempi - 1;
                                route[i1] = tempi + "" + tempj;
                                flag.put(route[i1], "");
                                i1++;
                            } else if (tempi + 1 < row && flag.get((tempi + 1) + "" + tempj) == null && map[tempi + 1][tempj] == traceChars[i1]) {
                                //下
                                tempi = tempi + 1;
                                route[i1] = tempi + "" + tempj;
                                flag.put(route[i1], "");
                                i1++;
                            } else if (tempj - 1 >= 0 && flag.get(tempi + "" + (tempj - 1)) == null && map[tempi][tempj - 1] == traceChars[i1]) {
                                //左
                                tempj = tempj - 1;
                                route[i1] = tempi + "" + tempj;
                                flag.put(route[i1], "");
                                i1++;
                            } else if (tempj + 1 < column && flag.get(tempi + "" + (tempj + 1)) == null && map[tempi][tempj + 1] == traceChars[i1]) {
                                //右
                                tempj = tempj + 1;
                                route[i1] = tempi + "" + tempj;
                                flag.put(route[i1], "");
                                i1++;
                            } else {
                                //匹配不成功，回退
                                if (i1 - 1 >= 0) {
                                    flag.remove(route[i1]);
                                    i1--;
                                } else {
                                    tempFlag = false;
                                    break;
                                }
                            }
                        }
                        if (tempFlag) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
