package com.soul.alg.leetcode2.search;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 * <p>
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 *
 * @author wangkunwk
 * @version 2020/9/14
 */
public class MaxPointsInALine {

    private static int generateGCD(int a, int b) {

        System.out.println(a + " : " + b);
        if (b == 0) return a;
        else return generateGCD(b, a % b);

    }

    public static void main(String[] args) {

        System.out.println(generateGCD(10,6));

    }

}
