package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public int getBooksCount(int[] bookId) {
        return 0;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
