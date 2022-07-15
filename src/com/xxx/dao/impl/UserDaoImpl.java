package com.xxx.dao.impl;

import com.xxx.dao.UserDao;
import com.xxx.pojo.User;

public class UserDaoImpl extends BasicDao<User> implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        return querySingle(sql,User.class,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return querySingle(sql,User.class,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values (?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
