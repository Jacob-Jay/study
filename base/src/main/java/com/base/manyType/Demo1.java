package com.base.manyType;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-05 17:14
 */
public class Demo1<T> {
    private T t1;
    private T t2;
}

class Demo2{
    public static  <T>void say(T t) {
        System.out.println(t);
    }
}
