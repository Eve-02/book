package com.xxx.Test;

import com.xxx.dao.OrderItemDao;
import com.xxx.dao.impl.OrderItemDaoImpl;
import com.xxx.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        int java = orderItemDao.saveOrderItem(new OrderItem(null, "java", 1, new BigDecimal(100), new BigDecimal(100), "123456789"));
        int java1 = orderItemDao.saveOrderItem(new OrderItem(null, "java1", 1, new BigDecimal(100), new BigDecimal(100), "123456789"));
        int java2 = orderItemDao.saveOrderItem(new OrderItem(null, "java2", 1, new BigDecimal(100), new BigDecimal(100), "123456789"));
        System.out.println(java);
        System.out.println(java1);
        System.out.println(java2);
    }
}