package org.example.jdbcdemo.service;



import org.example.jdbcdemo.model.Book;

import java.util.Collection;

public interface BookService {
    Book addBook(Book book);
    Book getBook(int id);
    void deleteBook(int id);
    void updateBook(int id, Book book);
    Collection<Book> getAll();
}
