package com.paximum.storeproject.controller;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() { return bookService.getBooks(); }

    @GetMapping("/book/{productId}")
    public Book getBookByProductId(@PathVariable int productId) {
        return bookService.getBookById(productId);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/addBooks")
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return bookService.addBooks(books);
    }

    @PostMapping("/updateBook")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }

}
