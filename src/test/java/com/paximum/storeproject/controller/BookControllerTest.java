package com.paximum.storeproject.controller;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.service.BookService;
import com.paximum.storeproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
//@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void getProducts_listOfChildEntities() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
        Book book = new Book();
        book.setName("testBookName");
        book.setGenre("testBookGenre");
        book.setReleaseDate(LocalDate.parse("2000-01-01"));
        book.setBasePrice(10.0f);
        book.setISBN("9780743273565");
        book.setWriterName("testWriterName");

        bookService.addBook(book);

        List<Book> books = new ArrayList<Book>();
        books.add(book);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
