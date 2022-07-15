package com.xxx.service;

import com.xxx.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user 用户
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user 用户
     * @return 如果返回null，则登陆失败；否则登陆成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username 用户名
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existUsername(String username);
}
