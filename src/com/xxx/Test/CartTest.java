package com.xxx.Test;

import com.xxx.pojo.CartItem;
import com.xxx.pojo.Cart;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {


    @Test
    public void test() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"111",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"111",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"222",1,new BigDecimal(500),new BigDecimal(500)));
//        cart.deleteItem(1);
//        cart.clear();
        cart.updateCount(1,3);
        System.out.println(cart);
    }

}