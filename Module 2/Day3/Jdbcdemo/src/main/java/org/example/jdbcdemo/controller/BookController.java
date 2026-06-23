package org.example.jdbcdemo.controller;

import org.example.jdbcdemo.model.Book;
import org.example.jdbcdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    // response entity
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
        // return ResponseEntity.ok(bookService.getBook(id));
    }

//    @GetMapping("/{id}")
//    public Book getBook(@PathVariable int id) {
//        return bookService.getBook(id);
//    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
       return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }
//    @GetMapping
//    public Collection<Book> getAllBooks() {
//        return bookService.getAll();
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
       // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable int id) {
//        bookService.deleteBook(id);
//    }

//    @PostMapping
//    public void saveBook(@RequestBody Book book){
//        bookService.addBook(book);
//    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
        bookService.updateBook(id, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
       // return ResponseEntity.ok(book);
    }


//    @PutMapping("/{id}")
//    public void updateBook(@RequestBody Book book){
//        bookService.updateBook(book.getId(), book);
//    }

}
