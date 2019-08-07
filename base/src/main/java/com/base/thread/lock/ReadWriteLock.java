package com.base.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-07 16:00
 */
public class ReadWriteLock {
    private static int i = 0;
    static java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();
    public static void main(String[] args) {
t1();
    }

    public static void t1() {




        for(int  j = 0;j< 10;j++){
            new Thread(()->{
               read();
            }).start();
            new Thread(()->{
                write();
            }).start();
        }
    }

    public static void read(){
        readLock.lock();  //共享读锁，排斥写锁，所以读出来都是10的整数
        try {
            System.out.println(i);
        } finally {
            readLock.unlock();
        }
    }

    public static void write() {
        writeLock.lock(); //排斥读写锁
        try {
            for (int j = 0;j<10;j++) {
                i++;
            }
        } finally {
            writeLock.unlock();
        }
    }
}
