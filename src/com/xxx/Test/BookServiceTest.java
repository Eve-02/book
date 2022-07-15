package com.xxx.Test;

import com.xxx.pojo.Book;
import com.xxx.service.BookService;
import com.xxx.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;



public class BookServiceTest {

    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        Book book = new Book(null ,"java从入门到放弃111" , "aaa" , new BigDecimal(800), 11111 , 9 , "static/img/default.jpg");
        bookService.addBook(book);
    }

    @Test
    public void deleteBookById() {
        Integer id = 11;
        bookService.deleteBookById(id);
    }

    @Test
    public void updateBook() {
        Book book = new Book(21 ,"入门" , "aaa" , new BigDecimal(800), 11111 , 9 , "static/img/default.jpg");
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }
}