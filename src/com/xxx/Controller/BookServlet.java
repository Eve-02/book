package com.xxx.Controller;

import com.xxx.pojo.Book;
import com.xxx.pojo.Page;
import com.xxx.service.BookService;
import com.xxx.service.impl.BookServiceImpl;
import com.xxx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页的功能
     * @param req
     * @param resp
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) {
        // 1.获取请求的参数 pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2.调用 BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");

        // 3.保存 page对象到 request域中
        req.setAttribute("page",page);

        // 4.请求转发到 /pages/manager/book_manager.jsp页面中
        try {
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo++;
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.addBook(book);
        try {
            /* 重定向到端口号,添加书后重定向刷新页面(重定向是再次请求)(请求转发是一次请求) */
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        try {
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp){
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book",book);
        try {
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) {
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.updateBook(book);
        try {
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        try {
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
