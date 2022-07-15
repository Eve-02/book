package com.xxx.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    // 静态代码块完成初始化
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 得到连接
    public static Connection getConnection() {
        Connection conn = conns.get();

        if(conn == null){
            try {
                conn = dataSource.getConnection(); // 从数据库连接池中取得连接

                // 保存到 ThreadLocal中
                conns.set(conn);

                conn.setAutoCommit(false);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return conn;
    }

    // 提交事务，并关闭释放连接
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){ // 说明之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交事务
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 释放连接
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 一定要执行，不然会出错 tomcat服务器底层使用了线程池
        conns.remove();
    }

    // 回滚事务，并关闭释放连接
    // 提交事务，并关闭释放连接
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){ // 说明之前使用过连接，操作过数据库
            try {
                connection.rollback(); // 回滚事务
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 释放连接
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 一定要执行，不然会出错 tomcat服务器底层使用了线程池
        conns.remove();
    }

    // 关闭连接(放回连接池)(Connection是接口! 这里的connection.close()方法
    // 并不是原来mysql实现的connection.close()方法，而是Druid(德鲁伊)实现的connection.close()方法，仅仅只是放回连接池)
//    public static void close(ResultSet resultSet, Statement statement, Connection connection){
//        try {
//            if(resultSet != null){
//                resultSet.close();
//            }
//            if(statement != null){
//                statement.close();
//            }
//            if(connection != null){
//                connection.close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

