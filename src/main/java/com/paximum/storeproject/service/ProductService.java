package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;
import com.paximum.storeproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public String getProductType(int productId) { return repository.findProductTypeByProductId(productId); }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int productId) {
        return repository.findById(productId).orElse(null);
    }

    public List<Product> getProductsByIds(List<Integer> ids) { return repository.findAllByProductIdIn(ids); }

    public String deleteProduct(int productId) {
        repository.deleteById(productId);
        return "item removed || productId: " + productId;
    }

    //public String getProductType2(int productId) { return repository.findById(productId).orElse(null).getDiscriminatorValue(); }

    public float getPricing(int productId) {
        Product product = repository.findById(productId).orElse(null);
        return product.pricing(product.getBasePrice());
    }

    public Product updateProduct(Product product) {
        Product updatedProduct = repository.findById(product.getProductId()).orElse(null);
        updatedProduct.setName(product.getName());
        //
        //
        //
        //
        return repository.save(updatedProduct);
    }

    public float findLowestPrice(List<Product> products) {
        Product product = products.stream().min((first, second) ->
                Float.compare(first.pricing(first.getBasePrice()), second.pricing(second.getBasePrice()))).get();
        return product.pricing(product.getBasePrice());
    }

    public float purchase(List<Item> items) {
        List<Integer> idList = items.stream().map(Item::getProductId).collect(Collectors.toList());
        List<Product> boughtProducts = getProductsByIds(idList);
        float totalPrice = getTotalPrice(boughtProducts, items);



        if(items.stream().mapToInt(Item::getCount).sum() >= 2) {
            if(getBookCount(boughtProducts, items) >= 2) {
                return totalPrice - findLowestPrice(getBoughtBooks(boughtProducts, items));
            }
            else {
                return totalPrice - findLowestPrice(boughtProducts)/2;
            }
        }
        else {
            return totalPrice;
        }
    }

    public float getTotalPrice(List<Product> boughtProducts, List<Item> items) {
        float price = 0;
        for(int i = 0; i < boughtProducts.size(); i++) {
            price += boughtProducts.get(i).pricing(boughtProducts.get(i).getBasePrice()) * items.get(i).getCount();
        }
        return price;
    }

    public int getBookCount(List<Product> boughtProducts, List<Item> items) {
        int count = 0;
        for(int i = 0; i < boughtProducts.size(); i++) {
            System.out.println("initialname: " + boughtProducts.get(i).getClass().getSimpleName());
            if(boughtProducts.get(i) instanceof Book) {
                count += items.get(i).getCount();
                System.out.println("loopcount: " + count);
            }
        }
        System.out.println("bookcount: " + count +
                ", boughProducts.size: " + boughtProducts.size() +
                ", getClassName of 0.Item: " + boughtProducts.get(0).getClass().getSimpleName() +
                ", items.size: " + items.size() +
                ", itemscount: " + items.get(0).getCount());
        return count;
    }

    public List<Product> getBoughtBooks(List<Product> boughtProducts, List<Item> items) {
        List<Product> boughtBooks = new ArrayList<Product>();
        for(int i = 0; i < boughtProducts.size(); i++) {
            if(boughtProducts.get(i) instanceof Book) {
                boughtBooks.add(boughtProducts.get(i));
            }
        }
        return boughtBooks;
    }

    //public void campaign()

}
