package com.northernarc.restapidemo.dao;

import com.northernarc.restapidemo.model.Book;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookDaoImpl implements BookDao {

    private Map<Integer, Book> books;

    @PostConstruct
    public void init() {
        System.out.println("Initializing Books Data...");
        books = new HashMap<>();
        books.put(1, new Book(1, "Adhipurush", "Ohm Raut", "Prabhas"));
        books.put(2, new Book(2, "Khaleja", "Trivikram", "NTR Productions"));
        books.put(3, new Book(3, "Pushpa", "Sukumar", "Allu Arjun"));
        books.put(4, new Book(4, "RRR", "Rajamouli", "Ram Charan"));
        books.put(5,new Book(5, "Bahubali", "Rajamouli", "Prabhas"));
    }


    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Books Data...");
        books.clear();
    }

    @Override
    public Book addBook(Book book) {
        books.put(book.getId(), book);
        return books.get(book.getId());
    }

    @Override
    public Book findBookById(int id) {
        return books.get(id);
    }

    @Override
    public void updateById(int id, Book book) {
        books.put(id, book);
    }

    @Override
    public void deleteById(int id) {
        books.remove(id);
    }

    @Override
    public Collection<Book> findAllBooks() {
        return books.values();
    }

    @Override
    public void deleteAllBooks() {
        books.clear();
    }
}
