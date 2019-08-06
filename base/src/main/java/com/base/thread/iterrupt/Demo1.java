package com.base.thread.iterrupt;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/6 21:15
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
              int  i = 0;
              //不会清除打断状态
//                while (!(Thread.currentThread().isInterrupted())) {
                //会清除打断状态
                    while (!(Thread.interrupted())) {
                    System.out.println(i++);

                }
                System.out.println(Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //System.exit(0);
                }
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t.start();
        System.out.println("main");
        Thread.sleep(10);
        t.interrupt();//中止线程但是线程不能阻塞
    }
}
