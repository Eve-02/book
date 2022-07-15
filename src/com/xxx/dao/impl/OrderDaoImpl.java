package com.xxx.dao.impl;

import com.xxx.dao.OrderDao;
import com.xxx.pojo.Order;

public class OrderDaoImpl extends BasicDao<Order> implements OrderDao {


    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) value (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
