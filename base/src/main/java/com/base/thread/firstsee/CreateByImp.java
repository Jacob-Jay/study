package com.base.thread.firstsee;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-30 16:36
 */
public class CreateByImp {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadImpl());
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main========="+i);
        }


    }
}
class ThreadImpl implements Runnable{

    @Override
    public void run() {
        int i = 0;
        while (i < 1000) {
            i++;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取数据完毕");
    }
}
