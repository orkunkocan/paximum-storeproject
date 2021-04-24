package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int productId) {
        return repository.findById(productId).orElse(null);
    }

    public String getProductType(int productId) { return repository.findProductTypeByProductId(productId); }

    public List<Product> getProductsByIds(List<Integer> ids) { return repository.findAllByProductIdIn(ids); }

    public String deleteProduct(int productId) {
        repository.deleteById(productId);
        return "item removed || productId: " + productId;
    }

    //returns pricing of a product baseprice{(Book +=2), (Film *=1.15), (MusicAlbum *=0.95)}
    public float getPricing(int productId) {
        Product product = repository.findById(productId).orElse(null);
        return product.pricing(product.getBasePrice());
    }

    //Finds lowest price in a given product list
    public float findLowestPrice(List<Product> products) {
        Product product = products.stream().min((first, second) ->
                Float.compare(first.pricing(first.getBasePrice()), second.pricing(second.getBasePrice()))).get();
        return product.pricing(product.getBasePrice());
    }

    //Main purchase operation
    public float purchase(List<Item> items) {
        List<Integer> idList = items.stream().map(Item::getProductId).collect(Collectors.toList());
        List<Product> boughtProducts = getProductsByIds(idList);
        float totalPrice = getTotalPrice(boughtProducts, items);
        if(items.stream().mapToInt(Item::getCount).sum() >= 2) {
            if(getBookCount(boughtProducts, items) >= 2) {
                return totalPrice - findLowestPrice(getBoughtBooks(boughtProducts)); //"2 books campaign" (cheapest book is free)
            } else {
                return totalPrice - findLowestPrice(boughtProducts)/2; //"2 product campaign" (cheapest item is half-price)
            }
        } else {
            return totalPrice; //No campaign
        }
    }

    //returns Total price of given products before campaign
    public float getTotalPrice(List<Product> boughtProducts, List<Item> items) {
        float price = 0;
        for(int i = 0; i < boughtProducts.size(); i++) {
            price += boughtProducts.get(i).pricing(boughtProducts.get(i).getBasePrice()) * items.get(i).getCount();
        }
        return price;
    }

    //Finds book count in a give product list
    public int getBookCount(List<Product> boughtProducts, List<Item> items) {
        int count = 0;
        for(int i = 0; i < boughtProducts.size(); i++) {
            if(boughtProducts.get(i) instanceof Book) {
                count += items.get(i).getCount();
            }
        }
        return count;
    }

    //Returns books to find lowest book price from product list
    public List<Product> getBoughtBooks(List<Product> boughtProducts) {
        List<Product> boughtBooks = new ArrayList<Product>();
        for(int i = 0; i < boughtProducts.size(); i++) {
            if(boughtProducts.get(i) instanceof Book) {
                boughtBooks.add(boughtProducts.get(i));
            }
        }
        return boughtBooks;
    }
}
