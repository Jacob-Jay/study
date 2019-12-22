package com.jq.service.impl;

import com.jq.dao.Order;
import com.jq.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/12/22 14:38
 */
public class OrderServiceImpl implements OrderService {
    private static final List<Order> ORDER_LIST = new ArrayList<Order>();

    static {
        ORDER_LIST.add(new Order("1",new BigDecimal("1.1")));
        ORDER_LIST.add(new Order("2",new BigDecimal("2.1")));
        ORDER_LIST.add(new Order("3",new BigDecimal("3.1")));
        ORDER_LIST.add(new Order("4",new BigDecimal("4.1")));
    }
    public List<Order> getOrder() {
        return ORDER_LIST;
    }
}
