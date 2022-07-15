package com.xxx.Test;

import com.xxx.dao.OrderDao;
import com.xxx.dao.impl.OrderDaoImpl;
import com.xxx.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {

        OrderDao orderDao = new OrderDaoImpl();
        int i = orderDao.saveOrder(new Order("123456789", new Date(), new BigDecimal(100), 0, 1));
        System.out.println(i);

    }
}