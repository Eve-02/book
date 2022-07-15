package com.xxx.Test;

import com.xxx.pojo.User;
import com.xxx.service.UserService;
import com.xxx.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"aewawfa","awefawf","45678@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin",null)));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("admin")){
            System.out.println("用户名存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}