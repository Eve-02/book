package com.xxx.Controller;

import com.google.gson.Gson;
import com.xxx.pojo.User;
import com.xxx.service.UserService;
import com.xxx.service.impl.UserServiceImpl;
import com.xxx.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp){

        User user = WebUtils.copyParamToBean(new User(),req.getParameterMap());
        User loginUser = userService.login(user);

        if(loginUser != null){
            try {
                System.out.println("登陆成功!");
                HttpSession session =  req.getSession();
                session.setAttribute("user",loginUser);
                session.setMaxInactiveInterval(60 * 60);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            // 将错误信息同用户名保存在域中回传给jsp页面显示
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",user.getUsername());
            try {
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void regist(HttpServletRequest req, HttpServletResponse resp){
        // 获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(new User(),req.getParameterMap());
        String code = req.getParameter("code");

        // 验证码写死为abc
        if(token != null && token.equalsIgnoreCase(code)){
            if(userService.existUsername(user.getUsername())){
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                try {
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                userService.registerUser(user);
                try {
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            try {
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void logout(HttpServletRequest req,HttpServletResponse resp){
        // 1.销毁Session中用户登陆的信息(或者销毁Session)
        req.getSession().invalidate();
        // 2.重定向到首页(或者登陆界面)
        try {
            resp.sendRedirect(req.getContextPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ajaxExistsUsername(HttpServletRequest req,HttpServletResponse resp){
        // 判断
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        // 把返回的结果封闭为Map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        String JsonString = new Gson().toJson(resultMap);
        try {
            resp.getWriter().write(JsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
