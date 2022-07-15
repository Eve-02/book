package com.xxx.dao;

import com.xxx.pojo.OrderItem;

public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem 订单项
     * @return 影响的行数
     */
    public int saveOrderItem(OrderItem orderItem);

}
