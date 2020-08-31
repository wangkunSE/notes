package com.soul.alg.leetcode2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2020/8/6
 */
public class KthLargestNumber extends AbstractAlg {

    private static class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (null == nums || nums.length < k) {
                return 0;
            }
            List<Integer> list = new ArrayList<>(k);
            for (int i = 0; i < nums.length; i++) {
                int temp = nums[i];
                if (list.size() < k) {
                    findPositionAndInsert(temp, list);
                } else {
                    findPositionAndInsert(temp, list, k);
                }

            }
            return list.get(list.size() - 1);
        }

        private void findPositionAndInsert(int temp, List<Integer> list, int k) {
            int position = 0;
            boolean change = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < temp) {
                    position = i;
                    change = true;
                    break;
                }
            }

            if (change){
                list.remove(list.size() - 1);
                list.add(position, temp);
            }
        }

        private void findPositionAndInsert(int temp, List<Integer> list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < temp) {
                    list.add(i, temp);
                    return;
                }
            }
            list.add(temp);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{3,2,3,1,2,4,5,5,6};
        int kthLargest = solution.findKthLargest(arr, 4);
        System.out.println(kthLargest);

    }


}
