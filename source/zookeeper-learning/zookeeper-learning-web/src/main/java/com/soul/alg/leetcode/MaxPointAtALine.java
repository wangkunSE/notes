package com.soul.alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/16
 * @time: 下午7:39
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class MaxPointAtALine {
    public static void main(String[] args) {
        First first = new First();
//        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 0), new Point(3, 1), new Point(4, 2)};
//        int maxPoints = first.maxPoints(points);
//        System.out.println(maxPoints);

        System.out.println(first.generateGCD(3,1));

    }

    private static class First {
        public int maxPoints(Point[] points) {
            if (points == null) return 0;
            if (points.length <= 2) return points.length;

            Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
            int result = 0;
            for (int i = 0; i < points.length; i++) {
                map.clear();
                int overlap = 0, max = 0;
                for (int j = i + 1; j < points.length; j++) {
                    int x = points[j].x - points[i].x;
                    int y = points[j].y - points[i].y;
                    if (x == 0 && y == 0) {
                        overlap++;
                        continue;
                    }
                    int gcd = generateGCD(x, y);
                    if (gcd != 0) {
                        x /= gcd;
                        y /= gcd;
                    }

                    if (map.containsKey(x)) {
                        if (map.get(x).containsKey(y)) {
                            map.get(x).put(y, map.get(x).get(y) + 1);
                        } else {
                            map.get(x).put(y, 1);
                        }
                    } else {
                        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
                        m.put(y, 1);
                        map.put(x, m);
                    }
                    max = Math.max(max, map.get(x).get(y));
                }
                result = Math.max(result, max + overlap + 1);
            }
            return result;


        }

        private int generateGCD(int a, int b) {

            if (b == 0) return a;
            else return generateGCD(b, a % b);

        }
    }

    private static class Point {
        public int x;
        public int y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
