package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/17
 */
public class PrintOneToN {

    public static void main(String[] args) {
        int n = 4;
        new PrintOneToN().printOneToNNumber(n);
    }

    private void printOneToNNumber(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        while (!increaseNumber(number)) {
            printNumber(number);
        }
    }

    private boolean increaseNumber(char[] number) {
        int index = number.length - 1;
        int nTakeOver = 0;
        boolean isOverFlow = false;
        for (int i = index; i >= 0; i--) {
            int nSum = number[i] - '0' + nTakeOver;
            if (i == index) {
                nSum++;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isOverFlow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) ('0' + nSum);
                break;
            }
        }
        return isOverFlow;
    }

    private void printNumber(char[] number) {
        int i = 0;
        while (number[i] == '0') i++;
        for (; i < number.length; i++) {
            System.out.print((char) number[i]);
        }
        System.out.println();
    }
}
