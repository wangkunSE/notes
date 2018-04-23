package com.soul.iqiyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangkun1
 * @version 2018/4/19
 */
public class Main {

    public static void main(String[] args) {
        new Second().leastStepEqual();
    }

    private static class First {
        public void dictString() {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line == null || "".equals(line)) {
                return;
            }
            char[] target = line.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(target[target.length - 1]);
            for (int i = target.length - 2; i >= 0; i--) {
                if (target[i] >= sb.charAt(sb.length() - 1)) {
                    sb.append(target[i]);
                }
            }
            System.out.println(sb.reverse().toString());
        }
    }

    private static class Second {
        public void leastStepEqual() {
            Scanner sc = new Scanner(System.in);
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();

            int result = 0;
            if (A >= B && A >= C) {
                result = theLeastNumber(A, B, C);
            } else if (B >= A && B >= C) {
                result = theLeastNumber(B, A, C);
            } else if (C >= A && C >= B) {
                result = theLeastNumber(C, A, B);
            }
            if (A == B && A == C) {
                result = 0;
            }
            System.out.println(result);
        }

        private int theLeastNumber(int max, int b, int c) {
            int count = 0;
            while (max - b > 1) {
                b += 2;
                count++;
            }
            while (max - c > 1) {
                c += 2;
                count++;
            }
            if (((max - b == 0) && (max - c != 0)) || ((max - b != 0) && (max - c == 0))) {
                count += 2;
            } else {
                count++;
            }
            return count;
        }
    }

    private static class Third {

        public void candyCaseTypes() {
            Scanner sc = new Scanner(System.in);
            int types = sc.nextInt();
            int target = sc.nextInt();
            List<List<Integer>> limit = new ArrayList<>(types);
            for (int i = 0; i < types; i++) {
                int li = sc.nextInt();
                int ri = sc.nextInt();
                List<Integer> eachLimit = new ArrayList<>(2);
                eachLimit.add(li);
                eachLimit.add(ri);
                limit.add(eachLimit);
            }
            int result = getTheTypes(types, target, limit);
            System.out.println(result);
        }

        private int getTheTypes(int types, int target, List<List<Integer>> limit) {
            int total = 0;
            for (List<Integer> list : limit) {
                total += (list.get(1) - list.get(0));
            }
            if (total < target) {
                return 0;
            }
            for (int i = limit.get(0).get(0); i < limit.get(0).get(1); i++) {
                int newTarget = target - i;

            }

            return 0;
        }
    }
}
