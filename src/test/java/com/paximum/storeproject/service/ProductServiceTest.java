package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductsTest() {
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
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        assertEquals(3, productService.getProducts().size());
    }

    @Test
    public void getProductByIdTest() {
        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f);

        Mockito.when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        assertEquals(product, productService.getProductById(1));
    }

    @Test
    public void getProductTypeTest() {

        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f);

        Mockito.when(productRepository.findProductTypeByProductId(product.getProductId())).thenReturn("productType");

        productService.getProductType(product.getProductId());
        verify(productRepository, times(1)).findProductTypeByProductId(product.getProductId());
    }

    @Test
    public void getProductsByIdsTest() {
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
        Mockito.when(productRepository.findAllByProductIdIn(ids)).thenReturn(productList);
        assertEquals(productList, productService.getProductsByIds(ids));
    }

    @Test
    public void deleteProductTest() {

        productService.deleteProduct(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void getProductPricingTest() {
        Product product = new Product(1, "productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f);

        Mockito.when(productRepository.findById(product.getProductId())).thenReturn(java.util.Optional.of(product));
        assertEquals(product.pricing(product.getBasePrice()), productService.getPricing(product.getProductId()));
    }

    @Test
    public void findLowestPriceTest() {
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
        assertEquals(10.0f, productService.findLowestPrice(productList));

    }

    @Test
    public void getTotalPriceTest() {
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
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        items.add(new Item(2,2));
        items.add(new Item(3,3));
        assertEquals(100.0f, productService.getTotalPrice(productList, items));
    }

    @Test
    public void getBookCountTest() {
        List<Product> productList = new ArrayList<>();
        Book book1 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book2 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book3 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        book1.setProductId(1);
        book2.setProductId(2);
        book3.setProductId(3);
        productList.add(book1);
        productList.add(book2);
        productList.add(book3);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        items.add(new Item(2,2));
        items.add(new Item(3,3));
        assertEquals(6, productService.getBookCount(productList, items));
    }

    @Test
    public void getBoughtBooksTest() {
        List<Product> productList = new ArrayList<>();
        Book book1 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book2 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book3 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        book1.setProductId(1);
        book2.setProductId(2);
        book3.setProductId(3);
        productList.add(book1);
        productList.add(book2);
        productList.add(book3);
        assertEquals(3, productService.getBoughtBooks(productList).size());
    }

    @Test
    public void purchaseTwoBooksCampaignTest() {
        List<Product> productList = new ArrayList<>();
        Book book1 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "999999999", "testName");
        Book book2 = new Book("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 20.0f, "999999999", "testName");
        book1.setProductId(1);
        book2.setProductId(2);
        productList.add(book1);
        productList.add(book2);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        items.add(new Item(2,1));
        List<Integer> idList = Arrays.asList(1,2);
        Mockito.when(productService.getProductsByIds(idList)).thenReturn(productList);

        assertEquals(22.0f, productService.purchase(items));
    }

    @Test
    public void purchaseTwoProductsCampaignTest() {
        List<Product> productList = new ArrayList<>();
        Film film1 = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        Film film2 = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        film1.setProductId(1);
        film2.setProductId(2);
        productList.add(film1);
        productList.add(film2);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        items.add(new Item(2,1));
        List<Integer> idList = Arrays.asList(1,2);
        Mockito.when(productService.getProductsByIds(idList)).thenReturn(productList);

        assertEquals(17.25f, productService.purchase(items));
    }

    @Test
    public void purchaseNoCampaignTest() {
        List<Product> productList = new ArrayList<>();
        Film film1 = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        film1.setProductId(1);
        productList.add(film1);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,1));
        List<Integer> idList = Arrays.asList(1);
        Mockito.when(productService.getProductsByIds(idList)).thenReturn(productList);

        assertEquals(11.5f, productService.purchase(items));
    }
}
