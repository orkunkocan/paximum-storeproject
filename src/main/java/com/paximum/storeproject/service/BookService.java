package com.paximum.storeproject.service;

import com.paximum.storeproject.converter.BookConverter;
import com.paximum.storeproject.dto.BookDto;
import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookConverter bookConverter;

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public Book getBookById(int id) { return bookRepository.findById(id).orElse(null); }

    public BookDto getBookDtoById(int id) {
        return bookConverter.entityToDto(bookRepository.findById(id).orElse(null));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> addBooks(List<Book> books) { return bookRepository.saveAll(books); }

    public Book updateBook(Book book) {
        Book updatedBook = bookRepository.findById(book.getProductId()).orElse(null);
        updatedBook.setName(book.getName());
        updatedBook.setGenre(book.getGenre());
        updatedBook.setReleaseDate(book.getReleaseDate());
        updatedBook.setBasePrice(book.getBasePrice());
        updatedBook.setISBN(book.getISBN());
        updatedBook.setWriterName(book.getWriterName());
        return bookRepository.save(updatedBook);
    }

    public String deleteBook(int productId) {
        bookRepository.deleteById(productId);
        return "book deleted.";
    }


}
