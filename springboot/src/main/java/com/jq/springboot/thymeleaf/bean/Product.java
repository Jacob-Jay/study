package com.jq.springboot.thymeleaf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 16:05
 */
@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private BigDecimal price;

}
