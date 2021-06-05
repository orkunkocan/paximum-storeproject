package com.paximum.storeproject.controller;

import com.paximum.storeproject.dto.ProductDto;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list-products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/list-products-dto")
    public List<ProductDto> getProductsDto() {
        return productService.getProductsDto();
    }

    @GetMapping("/product/{productId}")
    public Product getProductByProductId(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/productType/{productId}")
    public String getProductTypeByProductId(@PathVariable int productId) {
        return productService.getProductType(productId);
    }

    @GetMapping("/productPricing/{productId}")
    public float getProductPricing(@PathVariable int productId) {
        return productService.getPricing(productId);
    }

    @PostMapping("/productsByIds")
    public List<Product> getProductsByIds(@RequestBody List<Integer> ids) {
        return productService.getProductsByIds(ids);
    }

    @PostMapping("/purchase")
    public float getTotalPrice(@RequestBody List<Item> items) {
        return productService.purchase(items);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
