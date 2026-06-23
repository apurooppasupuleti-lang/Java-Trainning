package com.northernarc.restapidemo.dao;

import com.northernarc.restapidemo.model.Book;

import java.util.Collection;

public interface BookDao {
    Book addBook(Book book);
    Book findBookById(int id);
    void updateById(int id, Book book);
    void deleteById(int id);
    Collection<Book> findAllBooks();
    void deleteAllBooks();
}
