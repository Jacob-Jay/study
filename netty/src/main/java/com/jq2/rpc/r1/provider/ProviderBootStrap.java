package com.jq2.rpc.r1.provider;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 10:17
 */
public class ProviderBootStrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        //context.refresh();
//        HelloService bean = context.getBean(HelloService.class);
//        System.out.println(bean.hello("jq"));
//        Environment environment = context.getBean(Environment.class);
//        System.out.println(environment.getProperty("zookeeper.address"));
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        context.close();
    }
}
