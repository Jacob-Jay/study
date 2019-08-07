package com.base.thread.lock;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-07 14:32
 */
public class SycLock {
    private volatile  int a = 10;
    public static void main(String[] args) {

    }

    public static void t1() throws InterruptedException {
        synchronized (SycLock.class) {
            String.class.wait();
        }
    }

    public  synchronized void t2() {

    }
}
