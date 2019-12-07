package com.jq.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-07 13:42
 */
public class SchduledDemo {

    public static void main(String[] args) {
//        t1();
//        t2();
        t3();
    }

    private static void t1() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        Runnable r = () -> {
            System.out.println(System.currentTimeMillis());
        };
        System.out.println(System.currentTimeMillis());
        service.schedule(r, 500, TimeUnit.MILLISECONDS);
    }
    private static void t2() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        Runnable r = () -> {
            System.out.println(System.currentTimeMillis());
            try {
                TimeUnit.MILLISECONDS.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println(System.currentTimeMillis());
        //在给定延迟后执行第一次，每个给定时间执行一次，如执行时间大于给定周期则在上一次执行完毕后马上执行
        service.scheduleAtFixedRate(r, 500, 1000,TimeUnit.MILLISECONDS);
    }
    private static void t3() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        Runnable r = () -> {
            System.out.println(System.currentTimeMillis());
            try {
                TimeUnit.MILLISECONDS.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println(System.currentTimeMillis());
        //给定延迟后开始执行第一个，在上一个任务结束后给定的周期延迟在执行下一个任务
        service.scheduleWithFixedDelay(r, 500, 1000,TimeUnit.MILLISECONDS);
    }
}
