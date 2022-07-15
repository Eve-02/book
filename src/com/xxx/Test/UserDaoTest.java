package com.xxx.Test;

import com.xxx.dao.UserDao;
import com.xxx.dao.impl.UserDaoImpl;
import com.xxx.pojo.User;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {


        if(userDao.queryUserByUsername("adminwe") != null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin") != null){
            System.out.println("登陆成功!");
        }else{
            System.out.println("用户名或密码错误，登陆失败!");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "admin", "123456", "12345@qq.com")));
    }
}