package com.xxx.dao.impl;

import com.xxx.dao.OrderItemDao;
import com.xxx.pojo.OrderItem;

public class OrderItemDaoImpl extends BasicDao<OrderItem> implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) value (?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
