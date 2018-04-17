package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/12
 */
public class ArrayBinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 3, 8, 9, 11};
        int target = 3;
        int index = new ArrayBinarySearch().find(arr, target);
        System.out.println(index);
    }

    private int find(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;
        while (low <= high) {
            middle = (low + high) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }
}
