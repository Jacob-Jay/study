package com.jq.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-07 11:29
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws Exception{
//        t1();
//        t2();



//        invokeAll();
        invokeAny();
    }

    private static Callable<String> createRunnable(String s) {
        return ()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        };
    }

    private static void invokeAny() throws Exception{
        List<Callable<String>> task = new ArrayList<>();
        task.add(createRunnable("t1"));
        task.add(createRunnable("t2"));
        task.add(createRunnable("t3"));
        ExecutorService service = Executors.newFixedThreadPool(2);
        String s = service.invokeAny(task);
//        String s = service.invokeAny(task,300,TimeUnit.MILLISECONDS);
        System.out.println(s);
    }

    private static void invokeAll() throws InterruptedException, ExecutionException {
        List<Callable<String>> task = new ArrayList<>();
        task.add(createRunnable("t1"));
        task.add(createRunnable("t2"));
        task.add(createRunnable("t3"));
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<String>> futureList = service.invokeAll(task);
//        List<Future<String>> futureList = service.invokeAll(task,510,TimeUnit.MILLISECONDS);
        for (Future<String> stringFuture : futureList) {
            System.out.println(stringFuture.get());
        }
    }

    private static void t2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> jq = executorService.submit(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s");
        }, "jq");
        String s = jq.get();
        System.out.println(s);
    }

    private static void t1() {
        ExecutorService service = Executors.newFixedThreadPool(3);

        for(int i = 0;i<5;i++) {
            int finalI = i;
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                   // e.printStackTrace();
                    return;
                }
                System.out.println("i'm"+ finalI);

            });
        }
        try {
            TimeUnit.MILLISECONDS.sleep(980);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        List<Runnable> runnables = service.shutdownNow();
//        System.out.println(runnables.size());
        System.out.println(service.isShutdown());
        while (true) {

            System.out.println(service.isTerminated());//只有关闭后并且所有任务已经执行完毕 才会返回true
        }

    }
}
