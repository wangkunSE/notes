package com.soul.alg.leetcode2.search;

/**
 * @author wangkunwk
 * @version 2020/8/26
 */
public class IsValidAnagram {

    static class Solution {
        public boolean isAnagram(String s, String t) {

            if (s == null || t == null
                    || s.length() < 1 || t.length() < 1
                    || s.length() != t.length()) {
                return false;
            }

            int[] flags = new int[128];
            for (int i = 0; i < s.length(); i++) {
                flags[s.charAt(i)]++;
            }

            for (int i = 0; i < t.length(); i++) {
                flags[t.charAt(i)]--;
                int cnt = flags[t.charAt(i)];
                if (cnt < 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (solution){
                    System.out.println("wake up task!");
                    solution.notify();
                }

            }
        }).start();
        synchronized (solution) {
            try {
                System.out.println("begin waiting....");
                solution.wait();
                System.out.println("task done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
