package com.soul.alg.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangkun1
 * @version 2018/3/27
 */
public class InputUtils {

    /**
     * 根据需要输入的数组个数生成一个包含所有整型数组的集合
     *
     * @param arrNum    数组个数
     * @return          整形数组集合
     */
    public static List<Integer[]> getIntegerArraysBySize(int arrNum) {
        if (arrNum <= 0) {
            return new ArrayList<>();
        }
        Scanner scanner = new Scanner(System.in);
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < arrNum; i++) {
            int size = scanner.nextInt();
            scanner.nextLine();
            Integer[] tempArr = new Integer[size];
            for (int j = 0; j < size; j++) {
                tempArr[j] = scanner.nextInt();
            }
            result.add(tempArr);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer[]> list = getIntegerArraysBySize(1);
        for (Integer[] integers : list) {
            for (Integer integer : integers) {
                System.out.println(integer);
            }
        }
    }
}
