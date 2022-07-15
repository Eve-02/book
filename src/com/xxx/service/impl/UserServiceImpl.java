package com.xxx.service.impl;

import com.xxx.dao.UserDao;
import com.xxx.dao.impl.UserDaoImpl;
import com.xxx.pojo.User;
import com.xxx.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
