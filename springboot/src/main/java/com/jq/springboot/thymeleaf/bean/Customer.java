package com.jq.springboot.thymeleaf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
}
