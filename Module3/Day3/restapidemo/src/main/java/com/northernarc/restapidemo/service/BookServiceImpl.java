package com.northernarc.restapidemo.service;

import com.northernarc.restapidemo.dao.BookDao;
import com.northernarc.restapidemo.model.Book;
import com.northernarc.restapidemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Book addBook(Book book) {
        // validation logic
        return bookDao.addBook(book);
    }

    @Override
    public Book getBook(int id) {
        // validation logic
        return bookDao.findBookById(id);
    }

    @Override
    public void deleteBook(int id) {
        // validation logic
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        // validation logic
        bookDao.updateById(id, book);
    }

    @Override
    public Collection<Book> getAll() {
        // validation logic
        return bookDao.findAllBooks();
    }


}
