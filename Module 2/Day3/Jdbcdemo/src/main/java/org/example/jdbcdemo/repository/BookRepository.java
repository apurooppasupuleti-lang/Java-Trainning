package org.example.jdbcdemo.repository;

import org.example.jdbcdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

    @Repository
    public interface BookRepository extends JpaRepository<Book, Integer> {

        default Book addBook(Book book) {
            return save(book);
        }
        default Book findBookById(int id) {
            return findById(id).orElse(null);
        }

        default void updateById(int id, Book book) {
            book.setId(id);
            save(book);
        }

        default Collection<Book> findAllBooks() {
            return findAll();
        }
    }


