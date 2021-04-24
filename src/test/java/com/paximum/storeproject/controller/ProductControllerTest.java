package com.paximum.storeproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.BookRepository;
import com.paximum.storeproject.repository.ProductRepository;
import com.paximum.storeproject.service.ProductService;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ProductController.class)
//@SpringBootTest
//@WebAppConfiguration
public class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getProductsTest() throws Exception {
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
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    /*
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, clazz);
    }

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

    @Test
    public void getProductByIdTest() throws Exception {
        //setUp();
        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f);
        Mockito.when(productService.getProductById(1)).thenReturn(product);
        String url = "/api/v1/product/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(product);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void getProductTypeByIdTest() throws Exception {
        //setUp();
        int id = 1;
        Mockito.when(productService.getProductType(id)).thenReturn("productType");
        String url = "/api/v1/productType/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        assertEquals("productType", actualJsonResponse);
    }

    @Test
    public void getProductPricingTest() throws Exception {
        //setUp();
        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f);
        Mockito.when(productService.getPricing(product.getProductId()))
                .thenReturn(product.pricing(product.getBasePrice()));
        String url = "/api/v1/productPricing/" + product.getProductId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(String.valueOf(product.pricing(product.getBasePrice())), actualJsonResponse);
    }

    @Test
    public void getProductsByIdsTest() throws Exception {
        //setUp();
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
        List<Integer> ids = Arrays.asList(1,2,3);
        Mockito.when(productService.getProductsByIds(ids)).thenReturn(productList);
        String url = "/api/v1/productsByIds";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(productList);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void getTotalPriceTest() throws Exception {
        //setUp();
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 0.0f);
        Mockito.when(productService.purchase(items)).thenReturn(0.0f);
        String url = "/api/v1/purchase";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(items)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("actual: " + actualJsonResponse);
        assertEquals(String.valueOf(product.pricing(product.getBasePrice())), actualJsonResponse);
    }

    @Test
    public void deleteProductTest() throws Exception {
        //setUp();
        int id = 1;
        Mockito.when(productService.deleteProduct(id)).thenReturn("item removed || productId: " + id);
        String url = "/api/v1/deleteProduct/" + id;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("result: " + result);
        //Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
    }

}
