package com.paximum.storeproject.controller;

import com.paximum.storeproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ProductControllerTest {
    
    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

}
