package com.base.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-07 9:14
 */
public class ReentLock {
    Integer j = 10;
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

         ReentLock reentLock = new ReentLock();

       for (int i = 0;i<10;i++) {
           LockThread thread = reentLock.new LockThread();

//           SycThread thread = reentLock.new SycThread();
           thread.start();

       }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(reentLock.j);
    }

    //使用ReentrantLock锁定
   private class LockThread extends Thread {

        @Override
        public void run() {


            for (int time = 0; time < 100; time++) {
                lock.lock();
                try {
                    j++;
                } finally {
                    lock.unlock();
                }
            }


        }

    }

    //使用synchronized锁定
    private class SycThread extends Thread{

        @Override
        public void run() {
                for (int time = 0;time<100;time++) {
                    synchronized (lock){
                        j++;
                    }

                }
        }
    }
}


