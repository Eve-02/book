package com.xxx.dao.impl;

import com.xxx.dao.BookDao;
import com.xxx.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BasicDao<Book> implements BookDao {


    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book WHERE id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where id=?";
        return querySingle(sql,Book.class,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryMany(sql,Book.class);
    }

    @Override
    public List<Book> queryByPrice(int min, int max) {
        String sql = "select * from t_book where price between ? and ?";
        return queryMany(sql,Book.class,min,max);
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        return queryMany(sql,Book.class,begin,pageSize);
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        return queryMany(sql,Book.class,min,max,begin,pageSize);
    }

}
