package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/7/26
 * @time: 上午10:34
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class ALiBaBa {
    public static void main(String[] args) {
        String[] locations = {"2,2", "2,8", "6,6","4,4"};
        int calculate = First.calculate(locations);
        System.out.println(calculate);

    }

    private static class First {
        private static class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        static int calculate(String[] locations) {
            Point[] points = convertToPoints(locations);
            Arrays.sort(points, Comparator.comparingInt(o -> o.x));
            List<Point> list = new ArrayList<>();
            Point temp = null;
            for (int i = 0; i < points.length; i++) {
                if (temp == null) {
                    temp = points[i];
                } else if (temp.x == points[i].x && temp.y < points[i].y) {
                    temp = points[i];
                } else if (temp.x != points[i].x || i == points.length - 1) {
                    list.add(new Point(temp.x, temp.y));
                    temp = points[i];
                }
            }
            if (temp != null) {
                list.add(new Point(temp.x, temp.y));
            }
            int distance = 0;
            distance += list.get(0).x + list.get(0).y;
            for (int i = 1; i < list.size(); i++) {
                Point point = list.get(i);
                Point point1 = list.get(i - 1);
                int distanceX = point.x - point1.x;
                int distanceY = 0;
                if (point1.y > point.y) {
                    distanceY = point1.y - point.y;
                } else {
                    distanceY = point.y - point1.y;
                }
                distance += distanceX + distanceY;
            }
            distance += list.get(list.size() - 1).x + list.get(list.size() - 1).y;
            return distance;
        }

        private static Point[] convertToPoints(String[] locations) {
            Point[] result = new Point[locations.length];
            for (int i = 0; i < locations.length; i++) {
                String[] split = locations[i].split(",");
                result[i] = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
            return result;
        }
    }
}
