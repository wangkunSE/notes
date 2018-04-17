package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/12
 */
public class NumberOfBinary {

    public static void main(String[] args) {
        int target = Integer.MIN_VALUE;
        int count = new NumberOfBinary().getOneNumber(target);
        System.out.println(count);
        System.out.println(Integer.MIN_VALUE - 1);
    }

    private int getOneNumber(int target) {
        int count = 0;

        while (target != 0) {
            count++;
            target = target & (target - 1);
        }

        return count;
    }
}
