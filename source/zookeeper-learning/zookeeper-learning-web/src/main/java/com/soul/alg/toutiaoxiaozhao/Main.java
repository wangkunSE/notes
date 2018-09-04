package com.soul.alg.toutiaoxiaozhao;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/12
 * @time: 上午10:04
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class Main {
    public static void main(String[] args) {
        First first = new First();
        first.footBall();
//        Fifth fifth = new Fifth();
//        fifth.watchLive();
    }

    private static class First {

        int[][] grid = null;
        boolean[][] hasSearch = null;


        public void footBall() {
            Scanner sc = new Scanner(System.in);
            String[] footGroundLimit = sc.nextLine().split(",");
            int m = Integer.parseInt(footGroundLimit[0]);
            int n = Integer.parseInt(footGroundLimit[1]);
            if (m < 1 || n < 1) {
                return;
            }
            grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                String[] row = sc.nextLine().split(",");
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(row[j]);
                }
            }
            hasSearch = new boolean[m][n];
            int max = 0;
            int count = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int[] tempCount = {0, 0, 0};
                        int persons = countPersons(grid, i, j, tempCount);
                        max = Math.max(persons, max);
                        if (map.get(tempCount[0] + "" + tempCount[1]) == null) {
                            map.put(tempCount[0] + "" + tempCount[1], persons);
                            count++;
                        }
                    }
                }
            }

            System.out.println(count + "," + max);

        }

        private int countPersons(int[][] grid, int i, int j, int[] result) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || hasSearch[i][j]) {
                return 0;
            }
            int count = 0;
            hasSearch[i][j] = true;
            count++;
            result[0] = Math.max(result[0], i);
            result[1] = Math.max(result[1], j);
            return count + countPersons(grid, i + 1, j, result) +
                    countPersons(grid, i, j + 1, result) +
                    countPersons(grid, i - 1, j, result) +
                    countPersons(grid, i, j - 1, result) +
                    countPersons(grid, i - 1, j - 1, result) +
                    countPersons(grid, i + 1, j - 1, result) +
                    countPersons(grid, i - 1, j + 1, result) +
                    countPersons(grid, i + 1, j + 1, result);
        }
    }

    private static class Second {
        public void checkArticle() {
            Scanner sc = new Scanner(System.in);
            int n = Integer.parseInt(sc.nextLine());
            if (n < 1) {
                return;
            }

            List<List<Integer>> list = new ArrayList<>();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < n; i++) {
                String[] valid = sc.nextLine().split(";");
                for (int j = 0; j < valid.length; j++) {
                    List<Integer> tempList = new ArrayList<>();
                    String[] point = valid[j].split(",");
                    tempList.add(Integer.parseInt(point[0]));
                    tempList.add(Integer.parseInt(point[1]));
                    list.add(tempList);
                }
            }

            int length = list.size();
            int[] start = new int[length];
            int[] end = new int[length];
            for (int i = 0; i < length; i++) {
                start[i] = list.get(i).get(0);
                end[i] = list.get(i).get(1);
            }

            Arrays.sort(start);
            Arrays.sort(end);

            for (int i = 0, j = 0; i < length; i++) {
                if (i == length - 1 || start[i + 1] > end[i]) {
                    result.append(start[j] + "," + end[i]);
                    if (i != length - 1) {
                        result.append(";");
                    }
                    j = i + 1;
                }
            }
            System.out.println(result.toString());
        }
    }

    private static class Fifth {
        private class Point {
            public int start;
            public int end;

            public Point(int x, int y) {
                this.start = x;
                this.end = y;
            }
        }

        public void watchLive() {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n < 1 || m < 1) {
                return;
            }
            int[] start = new int[n];
            int[] end = new int[n];

            for (int i = 0; i < 2 * n; i++) {
                if (i % 2 == 0) {
                    start[i / 2] = sc.nextInt();
                } else {
                    end[i / 2] = sc.nextInt();
                }
            }

            List<Point> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new Point(start[i], end[i]));
            }

            list.sort(Comparator.comparingInt(o -> o.start));

            int count = 0;
            for (int i = 0; i < n; i++) {
                int tempCount = 1;
                Point point = list.get(i);
                int j = i + 1;
                while (j < n) {
                    Point point1 = list.get(j);
                    if (point1.start >= point.end) {
                        tempCount++;
                        point = point1;
                    }
                    j++;
                }
                count = Math.max(count, tempCount);
            }

            System.out.println(count);
        }
    }
}
