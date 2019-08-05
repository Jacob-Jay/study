package com.jq.springboot.thymeleaf;

import com.jq.springboot.thymeleaf.bean.Customer;
import com.jq.springboot.thymeleaf.bean.Order;
import com.jq.springboot.thymeleaf.bean.OrderLine;
import com.jq.springboot.thymeleaf.bean.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 15:47
 *
 * 存放thymeleaf所需的数据在内存中
 */
@Component
public class JavaData implements InitializingBean{
    private static  Order ORDER = new Order();

    @Override
    public void afterPropertiesSet() throws Exception {
        ORDER.setId(1000);
        Customer customer = new Customer(1,"客户1");
        ORDER.setCustomer(customer);
        Set<OrderLine> orderLineset = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product(i,"product"+i,new BigDecimal(2.3));
            OrderLine orderLine = new OrderLine(product,10,new BigDecimal(30));
            orderLineset.add(orderLine);
        }
        ORDER.setOrderLines(orderLineset);

    }

    public static Order getORDER() {
        return ORDER;
    }
}
