package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/17
 */
public class OddNumberMoveFront {

    public static void main(String[] args) {
        int[] arr = {-1, -3, 4, 2, -5, -7};
        new OddNumberMoveFront().arrayMove(arr);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    private void arrayMove(int[] arr) {
        if (arr.length <= 0) {
            return;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            while (arr[low] % 2 != 0 && low < high) {
                low++;
            }
            while (arr[high] % 2 == 0 && low < high) {
                high--;
            }
            swap(arr, low, high);
        }
    }

    private void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }
}
