package com.xxx.dao;

import com.xxx.pojo.User;

public interface UserDao {



    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null,说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 如果返回-1，表示操作失败，其它是sql语句执行影响的行数
     */
    public int saveUser(User user);

}
