package com.paximum.storeproject.controller;

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

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProductByProductId(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/productType/{productId}")
    public String getProductTypeByProductId(@PathVariable int productId) {
        return productService.getProductType(productId);
    }

//    @GetMapping("/productType2/{productId}")
//    public String getProductType(@PathVariable int productId) {
////        return productService.getProductType2(productId);
//    }

    @GetMapping("/productPricing/{productId}")
    public float getProductPricing(@PathVariable int productId) {
        return productService.getPricing(productId);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/getProductsByIds")
    public List<Product> getProductsByIds(@RequestBody List<Integer> ids) {
        return productService.getProductsByIds(ids);
    }
    //get(0).getClass().getSimpleName();

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return productService.saveProducts(products);
    }

    @PostMapping("/purchase")
    public float getTotalPrice(@RequestBody List<Item> items) {
        return productService.purchase(items);
    }

    @PostMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
