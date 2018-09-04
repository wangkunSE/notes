package com.soul.alg.leetcode;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/8/21
 * @time: 下午3:46
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class FrogJump {
    public static void main(String[] args) {
        int[] stones = {0,1,3,6,10,13,14};
//        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        Second second = new Second();
        boolean b = second.canCross(stones);
//        First first = new First();
//        boolean b = first.canCross(stones);
        System.out.println(b);
    }

    private static class First {
        public boolean canCross(int[] stones) {
            if (stones == null || stones.length < 2) {
                return true;
            }
            int index = -1;
            int pre = stones[1] - stones[0];
            for (int i = 1; i < stones.length; ) {
                int minUnit = getTheIndex(stones, i, pre - 1);
                int curUnit = getTheIndex(stones, minUnit, pre);
                int maxUnit = getTheIndex(stones, curUnit, pre + 1);
                if (stones[maxUnit] == stones[i] + pre + 1) {
                    pre = maxUnit;
                    i = maxUnit;
                } else if (stones[curUnit] == stones[i] + pre) {
                    pre = curUnit;
                    i = curUnit;
                } else if (stones[minUnit] == stones[i] + pre - 1) {
                    pre = minUnit;
                    i = minUnit;
                } else {
                    return false;
                }
            }
            return true;
        }

        private int getTheIndex(int[] stones, int start, int count) {
            for (int i = start; i < stones.length; i++) {
                if (stones[i] == stones[start] + count) {
                    return i;
                } else if (stones[i] > stones[start] + count) {
                    return i - 1;
                }
            }
            return start;
        }
    }

    private static class Second {
        public boolean canCross(int[] stones) {
            if (stones == null) {
                return false;
            }
            if (stones.length <= 1) {
                return true;
            }
            if (stones[1] != 1) {
                return false;
            }
            if (stones.length <= 2) {
                return true;
            }
            int pre = stones[1] - stones[0];
            return dfs(stones, 1, pre);
        }

        private boolean dfs(int[] stones, int start, int pre) {
            if (start < stones.length - 1) {
                int minUnit = getTheIndex(stones, start, pre - 1);
                int curUnit = getTheIndex(stones, start, pre);
                int maxUnit = getTheIndex(stones, start, pre + 1);
                boolean result = false;
                if (maxUnit != start && stones[maxUnit] == stones[start] + pre + 1) {
                    result = dfs(stones, maxUnit, pre + 1);
                }
                if (result) {
                    return true;
                }
                if (curUnit != start && stones[curUnit] == stones[start] + pre) {
                    result = dfs(stones, curUnit, pre);
                }
                if (result) {
                    return true;
                }
                if (minUnit != start && stones[minUnit] == stones[start] + pre - 1) {
                    result = dfs(stones, minUnit, pre - 1);
                }
                if (result) {
                    return true;
                }
                return false;
            } else {
                return true;
            }
        }

        private int getTheIndex(int[] stones, int start, int count) {
            for (int i = start; i < stones.length; i++) {
                if (stones[i] == stones[start] + count) {
                    return i;
                } else if (stones[i] > stones[start] + count) {
                    return i - 1 < 0 ? 0 : i - 1;
                }
            }
            return start;
        }
    }
}
