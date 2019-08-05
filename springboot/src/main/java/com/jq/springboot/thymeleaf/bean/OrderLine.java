package com.jq.springboot.thymeleaf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 16:05
 */
@Data
@AllArgsConstructor
public class OrderLine {
    private Product product;
    private int amount;
    private BigDecimal price;
}
