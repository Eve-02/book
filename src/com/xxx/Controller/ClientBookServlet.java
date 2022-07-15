package com.xxx.Controller;

import com.xxx.pojo.Book;
import com.xxx.pojo.Page;
import com.xxx.service.BookService;
import com.xxx.service.impl.BookServiceImpl;
import com.xxx.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) {

        // 1.获取请求的参数 pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2.调用 BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");

        // 3.保存 page对象到 request域中
        req.setAttribute("page",page);

        // 4.请求转发到 /pages/manager/book_manager.jsp页面中
        try {
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) {
        // 1.获取请求的参数 pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        // 2.调用 BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格参数，加到分页条的地址中
        if(req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(stringBuilder.toString());

        // 3.保存 page对象到 request域中
        req.setAttribute("page",page);

        // 4.请求转发到 /pages/manager/book_manager.jsp页面中
        try {
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
