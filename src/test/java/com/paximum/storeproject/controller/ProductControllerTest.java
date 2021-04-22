package com.paximum.storeproject.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.BookRepository;
import com.paximum.storeproject.repository.ProductRepository;
import com.paximum.storeproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(ProductController.class)
//@SpringBootTest
//@WebAppConfiguration
public class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductService productService;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private ObjectMapper objectMapper;
    //@Autowired
    //WebApplicationContext webApplicationContext;

    protected void setUp() {
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void listProductsTest() throws Exception {
        setUp();
        List<Product> productList = new ArrayList<>();
        productList.add(
                new Product(1,"productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f));
        productList.add(
                new Product(2,"productName2", "productGenre2",
                        LocalDate.of(2020, 2, 2), 15.0f));
        productList.add(
                new Product(3,"productName3", "productGenre3",
                        LocalDate.of(2020, 3, 3), 20.0f));
        Mockito.when(productService.getProducts()).thenReturn(productList);
        String url = "/api/v1/list-products";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(productList);
        assertEquals(actualJsonResponse, expectedJsonResponse);
    }



    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, clazz);
    }

    /*
    @Test
    public void getProductsList() throws Exception {
        setUp();
        String uri = "/api/v1/list-products";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("response content: " + content);
        //mapFromJson(content)
        Product[] productList = mapFromJson(content, Product[].class);
        //Product[] productlist = mapFromJson(content, Product[].class);
        assertTrue(productList.length >= 0);
    }

     */



}
