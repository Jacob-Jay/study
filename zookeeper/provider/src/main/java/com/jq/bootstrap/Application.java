package com.jq.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 14:57
 */
public class Application {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:zoo.xml");

        System.in.read();

    }
}
