package com.jq.springboot.thymeleaf.bean;

import lombok.Data;

import java.util.Set;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 16:04
 */
@Data
public class Order {
    private int id;
    private  Customer customer;
    private Set<OrderLine> orderLines;
}
