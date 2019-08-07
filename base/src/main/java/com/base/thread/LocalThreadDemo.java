package com.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-07 15:32
 */
public class LocalThreadDemo {

    //每个线程获取的对象都是独立的
    public static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    public static void main(String[] args) {

       for(int i = 0;i<10;i++) {
           new Thread(() -> {
               format.remove();
               SimpleDateFormat dateFormat = format.get();
               System.out.println(dateFormat);
           }).start();
       }
    }
}
