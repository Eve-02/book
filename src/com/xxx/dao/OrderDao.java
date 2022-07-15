package com.xxx.dao;

import com.xxx.pojo.Order;

public interface OrderDao {

    /**
     * 保存订单
     * @param order 订单
     * @return 影响的行数
     */
    public int saveOrder(Order order);

}
