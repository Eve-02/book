package com.xxx.service.impl;

import com.xxx.dao.BookDao;
import com.xxx.dao.impl.BookDaoImpl;
import com.xxx.pojo.Book;
import com.xxx.pojo.Page;
import com.xxx.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        // 设置总记录数
        Integer pageTotalCount = bookDao.queryBooks().size();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        // 求当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        // 设置总记录数
        Integer pageTotalCount = bookDao.queryByPrice(min,max).size();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        // 求当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
