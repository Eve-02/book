package com.xxx.Controller;

import com.google.gson.Gson;
import com.xxx.pojo.Book;
import com.xxx.pojo.CartItem;
import com.xxx.pojo.Cart;
import com.xxx.service.BookService;
import com.xxx.service.impl.BookServiceImpl;
import com.xxx.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) {
        // 获取商品id
        Integer bookId = WebUtils.parseInt(req.getParameter("id"),0);
        // 查询该商品的信息
        Book book = bookService.queryBookById(bookId);
        // 转换为CarItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回商品列表
        try {
            resp.sendRedirect(req.getHeader("Referer"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除商品项
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) {
        // 获取商品编号
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        // 找到删除
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
            // 重定向回购物车
            try {
                resp.sendRedirect(req.getHeader("Referer"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 清空购物车
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
            try {
                resp.sendRedirect(req.getHeader("Referer"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 修改商品数量
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求的商品id、数量
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        Integer count = WebUtils.parseInt(req.getParameter("count"),1);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            cart.updateCount(id,count);
            try {
                resp.sendRedirect(req.getHeader("Referer"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) {
        // 获取商品id
        Integer bookId = WebUtils.parseInt(req.getParameter("id"),0);
        // 查询该商品的信息
        Book book = bookService.queryBookById(bookId);
        // 转换为CarItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        // 最后一个添加的商品名称
        Map<String,Object> map = new HashMap<>();
        map.put("lastName",cartItem.getName());
        map.put("totalCount",cart.getTotalCount());
        String JsonString = new Gson().toJson(map);
        try {
            resp.getWriter().write(JsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
