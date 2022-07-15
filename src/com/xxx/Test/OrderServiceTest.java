package com.xxx.Test;

import com.xxx.pojo.Cart;
import com.xxx.pojo.CartItem;
import com.xxx.service.OrderService;
import com.xxx.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"111",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"111",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"222",1,new BigDecimal(500),new BigDecimal(500)));
        OrderService orderService = new OrderServiceImpl();
        String order = orderService.createOrder(cart, 1);
        System.out.println(order);
    }
}