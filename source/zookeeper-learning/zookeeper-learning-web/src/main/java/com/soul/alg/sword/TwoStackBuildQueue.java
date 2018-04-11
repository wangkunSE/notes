package com.soul.alg.sword;

import java.util.Stack;

/**
 * @author wangkun1
 * @version 2018/4/11
 */
public class TwoStackBuildQueue {

    private static class StackQueue {

        private Stack<Integer> inStack;
        private Stack<Integer> outStack;
        private int length;

        public StackQueue(Stack<Integer> inStack, Stack<Integer> outStack, int length) {
            this.inStack = inStack;
            this.outStack = outStack;
            this.length = length;
        }

        public StackQueue(int length) {
            inStack = new Stack<>();
            outStack = new Stack<>();
            this.length = length;
        }

        public boolean enQueue(Integer element) {
            if (inStack.size() == length && outStack.size() == length) {
                throw new ArrayIndexOutOfBoundsException(length);
            }
            if (inStack.size() == length) {
                while (outStack.size() < length) {
                    outStack.push(inStack.pop());
                }
            }
            inStack.push(element);
            return true;
        }

        public Integer deQueue() {
            if (inStack.size() == 0 && outStack.size() == 0) {
                throw new ArrayIndexOutOfBoundsException(length);
            }
            if (outStack.size() == 0) {
                while (inStack.size() > 0) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }
    }

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue(3);
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        stackQueue.enQueue(4);
        stackQueue.enQueue(5);
        stackQueue.enQueue(6);
        for (int i = 0; i < stackQueue.length * 2; i++) {
            System.out.println(stackQueue.deQueue());
        }
    }
}
