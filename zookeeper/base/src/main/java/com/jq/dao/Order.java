package com.jq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 14:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order  implements Serializable {


    private static final long serialVersionUID = -3010047139091268619L;
    private String num;
    private BigDecimal price;
}
