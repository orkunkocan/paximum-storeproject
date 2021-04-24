package com.paximum.storeproject.service;


import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BookServiceTest { // Tests Service layer while mocking database

    @Autowired
    BookService bookService;
    @MockBean
    BookRepository bookRepository;

    @Test
    public void getBooksTest() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));
        Mockito.when(bookRepository.getBooks()).thenReturn(bookList);
        assertEquals(3, bookService.getBooks().size());
    }

    @Test
    public void getBookByIdTest() {
        Book book = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");

        Mockito.when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));
        assertEquals(book, bookService.getBookById(1));
    }

    @Test
    public void addBookTest() {
        Book book = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");

        Mockito.when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.addBook(book));
    }

    @Test
    public void addBooksTest() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));
        bookList.add(
                new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName"));

        Mockito.when(bookRepository.saveAll(bookList)).thenReturn(bookList);
        assertEquals(bookList, bookService.addBooks(bookList));
    }

    @Test
    public void updateBookTest() {
        Book book = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");

        Book updatedBook = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");

        book.setProductId(1);
        updatedBook.setProductId(1);
        Mockito.when(bookRepository.findById(book.getProductId())).thenReturn(java.util.Optional.of(updatedBook));
        bookService.updateBook(book);
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    public void deleteBookTest() {

        bookService.deleteBook(1);
        verify(bookRepository, times(1)).deleteById(1);
    }


}
