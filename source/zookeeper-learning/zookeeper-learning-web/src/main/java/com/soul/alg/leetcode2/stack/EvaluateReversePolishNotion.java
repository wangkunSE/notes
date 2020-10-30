package com.soul.alg.leetcode2.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression
 * would always evaluate to a result and there won't be any divide by
 * zero operation.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * @author wangkunwk
 * @version 2020/10/9
 */
public class EvaluateReversePolishNotion {

    static class Solution {

        private static List<String> operationSymbol;

        static {
            operationSymbol = new ArrayList<>();
            operationSymbol.add("+");
            operationSymbol.add("-");
            operationSymbol.add("*");
            operationSymbol.add("/");
        }


        public int evalRPN(String[] tokens) {

            if (Objects.isNull(tokens)) {
                return 0;
            }

            Deque<String> operationNum = new ArrayDeque<>();

            for (String token : tokens) {
                if (operationSymbol.contains(token)) {
                    String secondNum = operationNum.pop();
                    String firstNum = operationNum.pop();
                    operationNum.push(calc(firstNum, secondNum, token));

                } else {
                    operationNum.push(token);
                }
            }

            return Integer.parseInt(operationNum.pop());
        }

        private String calc(String firstNum, String secondNum, String token) {
            int first = Integer.parseInt(firstNum);
            int second = Integer.parseInt(secondNum);

            switch (token) {
                case "+": {
                    return String.valueOf(first + second);
                }
                case "-": {
                    return String.valueOf(first - second);
                }
                case "*": {
                    return String.valueOf(first * second);
                }
                case "/": {
                    if (second == 0) {
                        return 0 + "";
                    }
                    return String.valueOf(first / second);
                }
                default:
                    throw new RuntimeException("Wrong operateSymbol !");
            }


        }
    }

    static class SolutionII {
        public int evalRPN(String[] tokens) {
            if (tokens.length == 1) return toInt(tokens[0]);

            this.tokens = tokens;
            this.curs = tokens.length - 1;
            return subEvalRPN();
        }

        private String[] tokens;
        private int curs;


        private int subEvalRPN() {
            int b, a;
// 		int curss=curs;
            char operator = tokens[curs--].charAt(0);

            if (isNumber()) {
                b = toInt(tokens[curs--]);
            } else {
                b = subEvalRPN();
            }
            if (isNumber()) {
                a = toInt(tokens[curs--]);
            } else {
                a = subEvalRPN();
            }

// 		System.out.println("subExp "+curss+" through "+(curs+1)+" is evaluated to "+a+operator+b);

            switch (operator) {
                case '+':
                    return a + b;

                case '-':
                    return a - b;

                case '*':
                    return a * b;

                case '/':
                    return a / b;

                default:
                    return 0;
            }
        }

        private int toInt(String s) {
            int i = 0;
            int len = s.length();
            int ret = 0;
            boolean neg = false;
            if (s.charAt(0) == '-') {
                i++;
                neg = true;
            }
            for (; i < len; i++) {
                ret *= 10;
                ret += s.charAt(i) - '0';
            }
            return neg ? -ret : ret;

        }

        private boolean isNumber() {
            char tmp;

            return (tmp = tokens[curs].charAt(tokens[curs].length() - 1)) >= '0' && tmp <= '9';
        }
    }

}
