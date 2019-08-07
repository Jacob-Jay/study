package com.base.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-07 17:07
 */
public class Demo1 {
    public static void main(String[] args) {
        int size = 10000;
        double [] doubles = new double[size];
        for(int i = 0;i<size;i++)
            doubles[i] = Math.random();

        long l = System.currentTimeMillis();
        Demo1 demo1 = new Demo1();
        Coount coount = demo1.new Coount(doubles, 0, doubles.length, new Filt() {
            @Override
            public boolean accept(double b) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return b > 0.5;
            }
        });
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(coount);
        System.out.println(coount.join());
        System.out.println(System.currentTimeMillis()-l);

    }

    private interface Filt{
        boolean accept(double b);
    }

    private class Coount extends RecursiveTask<Integer> {
        public static final int size = 100;
        private double[]values;
        private int from;
        private int to;
        private Filt filt;

        public Coount(double[] values, int from, int to, Filt filt) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filt = filt;
        }

        @Override
        protected Integer compute() {
            if (to - from < size) {   //拆分度
                int count = 0;
                for(int i = from;i<to;i++) {
                    if (filt.accept(values[i])) {
                        count++;
                    }
                }
                return count;
            }else {
                int mid = (from+to)/2;
                Coount coount1 = new Coount(values, from, mid, filt);
                Coount coount2 = new Coount(values,  mid,to, filt);
                invokeAll(coount1, coount2);
                return coount1.join()+coount2.join();
            }
        }
    }
}
