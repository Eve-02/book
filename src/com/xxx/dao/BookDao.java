package com.xxx.dao;

import com.xxx.pojo.Book;

import java.util.Collection;
import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public List<Book> queryByPrice(int min,int max);

    public List<Book> queryForPageItems(int begin, int pageSize);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
