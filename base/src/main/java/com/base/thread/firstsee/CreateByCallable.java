package com.base.thread.firstsee;

import java.util.concurrent.*;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-30 16:52
 */
public class CreateByCallable {
    public static void main(String[] args) {
        ThreadCallable callable = new ThreadCallable<Integer>(); //1
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("finish");

//        Executor executor = new ThreadPoolExecutor();

    }
}

class ThreadCallable<T> implements Callable<T>{

    @Override
    public T call() throws Exception {
        T t = (T) new Integer(10);
        Thread.sleep(1000);
        return t;
    }
}