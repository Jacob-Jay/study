package com.base.thread.attribute;

import java.io.FileNotFoundException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/6 23:29
 */
public class Demo {
    public static void main(String[] args) {
        Thread sdsd = new Thread(new Runnable() {
            @Override
            public void run() {
//                throw new FileNotFoundException("");//不能抛出已检查异常
                throw new RuntimeException("sdsd");
            }
        });
        sdsd.setName("test error");
        sdsd.setUncaughtExceptionHandler(new Demo().new threadErrorHandler());
        sdsd.start();
    }

    private class threadErrorHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName()+"    "+e.getMessage());
        }
    }
}
