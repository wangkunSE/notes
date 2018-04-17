package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/12
 */
public class RotateArraySearch {

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        Integer min = new RotateArraySearch().findTheMinNumberOfRotateArray(arr);
        System.out.println(min);
    }

    private Integer findTheMinNumberOfRotateArray(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        while (arr[low] >= arr[high]) {
            if (high - low == 1) {
                middle = high;
                break;
            }
            middle = (low + high) / 2;
            if (arr[low] == arr[middle] && arr[middle] == arr[high]) {
                return MinInOrder(arr, low, high);
            }
            if (arr[middle] >= arr[low]) {
                //旋转点在右边
                low = middle;
            } else if (arr[middle] <= arr[high]) {
                //旋转点在左边
                high = middle;
            }
        }
        return arr[middle];
    }

    private Integer MinInOrder(int[] arr, int low, int high) {
        int min = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }
}
