package com.xxx.Controller;

import com.xxx.pojo.Cart;
import com.xxx.pojo.User;
import com.xxx.service.OrderService;
import com.xxx.service.impl.OrderServiceImpl;
import com.xxx.utils.JdbcUtils;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) {
        // 先获取Cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取UserId
        User user = (User) req.getSession().getAttribute("user");

        if(user == null){
            try {
                req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            Integer id = user.getId();
            // 调用OrderService生成订单
            String orderId = null;

            try {
                orderId = orderService.createOrder(cart, id);
                JdbcUtils.commitAndClose(); // 提交事务

            } catch (Exception e) {

                JdbcUtils.rollbackAndClose(); // 回滚事务
                throw new RuntimeException(e); // 把异常抛给Tomcat服务器，展示友好的错误提示页面
            }

            req.getSession().setAttribute("orderId",orderId);
            // 重定向(防止F5表单重复提交)
            try {
                resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void showAllOrder(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) {

    }

}
