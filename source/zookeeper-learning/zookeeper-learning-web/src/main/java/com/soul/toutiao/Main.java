package com.soul.toutiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author WK
 * @version 2018/3/24
 */
public class Main {

    public static void main(String[] args) {
//        first();
//        fourth();
        third();
    }

    private static void third() {
        Scanner sc = new Scanner(System.in);
        int rowNum = sc.nextInt();
        List<String> expressions = new ArrayList<>(rowNum);
        while (expressions.size() < rowNum) {
            expressions.add(sc.next());
        }
        List<String> targetGraph = new ArrayList<>(4);
        targetGraph.add("6 666 6");
        targetGraph.add(" .... 6");
        targetGraph.add("6 .... ");
        targetGraph.add("6 ... 6");
        List<Integer[]> numGraph = new ArrayList<>(10);
        numGraph.add(new Integer[]{1, 4, 4, 4, 1});
        numGraph.add(new Integer[]{2, 2, 2, 2, 2});
        numGraph.add(new Integer[]{1, 2, 1, 3, 1});
        numGraph.add(new Integer[]{1, 2, 1, 2, 1});
        numGraph.add(new Integer[]{4, 4, 1, 2, 2});
        numGraph.add(new Integer[]{1, 3, 1, 2, 1});
        numGraph.add(new Integer[]{1, 3, 1, 4, 1});
        numGraph.add(new Integer[]{1, 2, 2, 2, 2});
        numGraph.add(new Integer[]{1, 4, 1, 4, 1});
        numGraph.add(new Integer[]{1, 4, 1, 2, 1});
        for (String expression : expressions) {
            Integer result = getExpressionResult(expression);
            printGraphResult(result, targetGraph, numGraph);
            System.out.println();
        }
    }

    private static void printGraphResult(Integer result, List<String> targetGraph, List<Integer[]> numGraph) {
        char[] chars = result.toString().toCharArray();
        for (int i = 0; i < 5; i++) {
            Integer[] integers = printEachLine(chars, targetGraph, numGraph, i);
            for (int i1 = 0; i1 < integers.length; i1++) {
                System.out.print(targetGraph.get(integers[i1] - 1));
                if (i1 < integers.length - 1) {
                    System.out.print(" ..");
                }
            }
            System.out.println();
        }
    }

    private static Integer[] printEachLine(char[] chars, List<String> targetGraph, List<Integer[]> numGraph, int rowNum) {
        Integer[] target = new Integer[chars.length];
        for (int i = 0; i < chars.length; i++) {
            Integer integer = numGraph.get(Integer.parseInt(chars[i] + ""))[rowNum];
            target[i] = integer;
        }
        return target;
    }

    private static Integer getExpressionResult(String expression) {
        String[] split = expression.split("\\b");
        List<Integer> targetNums = new ArrayList<>();
        List<String> operations = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                targetNums.add(Integer.parseInt(split[i]));
            } else {
                operations.add(split[i]);
            }
        }
        for (int i = 0; i < operations.size(); ) {
            if ("*".equals(operations.get(i))) {
                int temp = targetNums.get(i) * targetNums.get(i + 1);
                targetNums.remove(i);
                targetNums.remove(i);
                targetNums.add(i, temp);
                operations.remove(i);
            } else {
                i++;
            }
        }
        for (int i = 0; i < operations.size(); ) {
            int temp = 0;
            if ("+".equals(operations.get(i))) {
                temp = targetNums.get(i) + targetNums.get(i + 1);
            } else {
                temp = targetNums.get(i) - targetNums.get(i + 1);
            }
            targetNums.remove(i);
            targetNums.remove(i);
            targetNums.add(i, temp);
            operations.remove(i);
        }
        return targetNums.get(0);
    }

    private static void fourth() {
        Scanner sc = new Scanner(System.in);
        Integer boardNum = 0;
        Integer chances = 0;
        Integer avail = 0;
        boardNum = sc.nextInt();
        chances = sc.nextInt();
        avail = sc.nextInt();
        List<Integer> boards = new ArrayList<>(boardNum);
        while (boards.size() < boardNum) {
            boards.add(sc.nextInt());
        }
        Collections.sort(boards);
        Collections.reverse(boards);
        Integer currentHeight = 0;
        for (int i = 0; i < chances; i++) {
            Integer board = getAvailBoard(currentHeight, boards, avail);
            if (board == null) {
                break;
            }
            currentHeight = currentHeight + (board - currentHeight) * 2;
        }

        System.out.println(currentHeight);

    }

    private static Integer getAvailBoard(Integer currentHeight, List<Integer> boards, Integer avail) {
        for (Integer board : boards) {
            if ((board - currentHeight) <= avail) {
                return board;
            }
        }
        return null;
    }

    private static void first() {
        Scanner sc = new Scanner(System.in);
        Integer size1 = 0;
        Integer size2 = 0;
        size1 = sc.nextInt();
        size2 = sc.nextInt();
        List<Integer> integerList1 = new ArrayList<>(size1);
        while (integerList1.size() < size1) {
            integerList1.add(sc.nextInt());
        }
        List<Integer> integerList2 = new ArrayList<>(size2);
        while (integerList2.size() < size2) {
            integerList2.add(sc.nextInt());
        }

        Integer result = 0;
        Double avg1 = getAvg(integerList1);
        Double avg2 = getAvg(integerList2);
        while (!Objects.equals(avg1, avg2)) {
            if (avg1 > avg2) {
                Integer target = getTheNumber(avg1, avg2, integerList1);
                if (target == null) {
                    break;
                }
                integerList2.add(target);
                integerList1.remove(target);
                result++;
            } else if (avg2 > avg1) {
                Integer target = getTheNumber(avg2, avg1, integerList2);
                if (target == null) {
                    break;
                }
                integerList2.remove(target);
                integerList1.add(target);
                result++;
            } else {
                break;
            }
            avg1 = getAvg(integerList1);
            avg2 = getAvg(integerList2);
        }

        System.out.println(result);
    }


    private static Integer getTheNumber(Double big, Double small, List<Integer> targetList) {
        if (Objects.equals(big, small)) {
            return null;
        }
        for (Integer integer : targetList) {
            if (integer < big && integer > small) {
                return integer;
            }
        }
        return null;
    }

    private static double getAvg(List<Integer> integerList1) {
        Integer sum = 0;
        for (Integer integer : integerList1) {
            sum += integer;
        }
        return (sum + 0.0) / (integerList1.size() + 0.0);
    }
}
