package org.example.jdbcdemo.service;

import org.example.jdbcdemo.repository.BookRepository;
import org.example.jdbcdemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public Book addBook(Book book) {
        // validation logic
        return bookRepository.addBook(book);
    }

    @Override
    public Book getBook(int id) {
        // validation logic

        return bookRepository.findBookById(id);
    }

    @Override
    public void deleteBook(int id) {
        // validation logic
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        // validation logic
        bookRepository.updateById(id, book);
    }

    @Override
    public Collection<Book> getAll() {
        // validation logic
        return bookRepository.findAllBooks();
    }


}
