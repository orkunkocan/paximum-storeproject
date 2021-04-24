package com.paximum.storeproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.service.BookService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(BookController.class)
public class BookControllerTest { //Tests connection layer with MockMvc, Uses Mockito for service

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getBooksTest() throws Exception {
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
        Mockito.when(bookService.getBooks()).thenReturn(bookList);
        String url = "/api/v1/books";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(bookList);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Mockito.when(bookService.getBookById(1)).thenReturn(book);
        String url = "/api/v1/book/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(book);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addBookTest() throws Exception {
        Book book = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Mockito.when(bookService.addBook(book)).thenReturn(book);
        String url = "/api/v1/addBook";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(book);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addBooksTest() throws Exception {
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
        Mockito.when(bookService.addBooks(bookList)).thenReturn(bookList);
        String url = "/api/v1/addBooks";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookList)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(bookList);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void updateBookTest() throws Exception {
        Book updatedBook = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");

        Mockito.when(bookService.updateBook(book)).thenReturn(updatedBook);
        String url = "/api/v1/updateBook";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(book);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void deleteBookTest() throws Exception {
        int id = 1;
        Mockito.when(bookService.deleteBook(id)).thenReturn("book deleted");
        String url = "/api/v1/deleteBook/" + id;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("result: " + result);
        //Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
    }



}
