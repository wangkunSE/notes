package com.soul.alg.sword;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wangkun1
 * @version 2018/4/11
 */
public class TwoQueueBuildStack {

    private static class TwoQueueStack<T> {
        private Queue<T> queue1;
        private Queue<T> queue2;
        private int length;

        public TwoQueueStack(Queue<T> queue1, Queue<T> queue2) {
            this.queue1 = queue1;
            this.queue2 = queue2;
            this.length = queue1.size();
        }

        public TwoQueueStack(int length) {
            this.queue1 = new ArrayBlockingQueue<T>(length);
            this.queue2 = new ArrayBlockingQueue<T>(length);
            this.length = length;
        }

        public boolean push(T element) {
            if ((queue1.size() + queue2.size()) == length) {
                throw new ArrayIndexOutOfBoundsException(length + 1);
            }
            if (queue1.size() == 0 && queue2.size() == 0) {
                queue1.add(element);
                return true;
            }
            if (queue1.size() > 0 && queue1.size() < length) {
                queue1.add(element);
                return true;
            } else if (queue2.size() > 0 && queue2.size() < length) {
                queue2.add(element);
                return true;
            } else {
                throw new ArrayIndexOutOfBoundsException(length + 1);
            }
        }

        public T pop() {
            if ((queue1.size() + queue2.size()) == 0) {
                throw new ArrayIndexOutOfBoundsException(-1);
            }
            if (queue1.size() > 0) {
                while (1 < queue1.size()) {
                    queue2.add(queue1.poll());
                }
                return queue1.poll();
            } else {
                while (1 < queue2.size()) {
                    queue1.add(queue2.poll());
                }
                return queue2.poll();
            }
        }
    }

    public static void main(String[] args) {
        TwoQueueStack<Integer> queueStack = new TwoQueueStack<>(4);
        queueStack.push(1);
        queueStack.push(2);
        queueStack.push(3);
        queueStack.push(4);
        queueStack.push(5);
        for (int i = 0; i < 4; i++) {
            System.out.println(queueStack.pop());
        }
    }
}
