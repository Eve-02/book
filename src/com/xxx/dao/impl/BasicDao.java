package com.xxx.dao.impl;

import com.xxx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BasicDao<T> {

    private QueryRunner qr = new QueryRunner();

    // 通用的 dml操作方法
    public int update(String sql, Object... parameters) {
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return qr.update(connection, sql, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回多个对象(多行)的通用方法
     * @param sql sql语句，可以有 ?
     * @param clazz 传入一个类的 Class对象，比如：news.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据 news.class 返回对应的 ArrayList集合，执行失败返回null
     */
    public List<T> queryMany(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return qr.query(connection, sql, new BeanListHandler<>(clazz), parameters); // List<T> query
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 查询单行结果的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return qr.query(connection, sql, new BeanHandler<>(clazz), parameters); // T Query
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 返回单行单列的通用方法
    public Object queryScalar(String sql, Object... parameters){
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
