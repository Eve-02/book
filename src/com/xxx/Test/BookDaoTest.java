package com.xxx.Test;

import com.xxx.dao.BookDao;
import com.xxx.dao.impl.BookDaoImpl;
import com.xxx.pojo.Book;
import org.junit.Test;


import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;


public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(null ,"java从入门到放弃" , "aaa" , new BigDecimal(800), 11111 , 9 , "static/img/default.jpg");
        int i = bookDao.addBook(book);
        if(i>0){
            System.out.println("添加成功~");
        }else{
            System.out.println("添加失败~");
        }
    }

    @Test
    public void deleteBookById() {
        Integer id = 12;
        bookDao.deleteBookById(id);
    }

    @Test
    public void updateBook() {
        Book book = new Book(21 ,"从入门到放弃" , "aaa" , new BigDecimal(800), 11111 , 9 , "static/img/default.jpg");
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(19);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        Stream<Book> stream = books.stream();
        System.out.println(stream.count());
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(8, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryByPrice(){
        System.out.println(bookDao.queryByPrice(10,50).size());
    }

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> books = bookDao.queryForPageItemsByPrice(0, 4,10,50);
        for (Book book : books) {
            System.out.println(book);
        }
    }


}