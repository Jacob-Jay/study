package com.jq.controller;

import com.jq.dao.Person;
import com.jq.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 15:04
 */
public class OrderController {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:consumer.xml");
        UserService bean = context.getBean(UserService.class);
        List<Person> people = bean.personList();
        System.out.println(people);


        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }
}
