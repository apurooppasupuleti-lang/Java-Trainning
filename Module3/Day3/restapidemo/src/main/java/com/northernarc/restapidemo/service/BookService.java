package com.northernarc.restapidemo.service;

import com.northernarc.restapidemo.model.Book;

import java.util.Collection;

public interface BookService {
    Book addBook(Book book);
    Book getBook(int id);
    void deleteBook(int id);
    void updateBook(int id, Book book);
    Collection<Book> getAll();
}
