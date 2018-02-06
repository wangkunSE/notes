package com.soul.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * @author wangkun1
 * @version 2018/2/6
 */
public class g_FutureTask {

    private static class AsynTask implements Callable<Long> {

        private Long maxNumber;

        public AsynTask(Long max) {
            this.maxNumber = max;
        }

        @Override
        public Long call() throws Exception {
            return factorial(maxNumber);
        }

        private Long factorial(Long maxNumber) {
            if (maxNumber <= 1) {
                return 1L;
            }
            Long result = 1L;
            for (int i = 1; i <= maxNumber; i++) {
                result *= i;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        getFactorialResult(100L);
    }

    private static void getFactorialResult(Long max) {
        FutureTask<Long> target = new FutureTask<Long>(new AsynTask(max));
        new Thread(target).start();
        while (!target.isDone()) {
            System.out.println("I am running!!");
        }
        try {
            System.out.println(target.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
