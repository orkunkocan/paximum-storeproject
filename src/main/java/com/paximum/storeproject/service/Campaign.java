package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Item;
import com.paximum.storeproject.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Campaign {
    List<Float> discounts = new ArrayList<>();
    float discount = 0;

    public void campaignTwoBook(List<Product> products, List<Item> items) {
        int count = 0;
        List<Product> productBooks = new ArrayList<>();
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i) instanceof Book) {
                productBooks.add(products.get(i));
                count += items.get(i).getCount();
            }
        }
        if (count >= 2) {
            discounts.add(findLowestPrice(productBooks));
        }
    }

    public void campaignTwoItem(List<Product> products, List<Item> items) {
        if(items.stream().mapToInt(Item::getCount).sum() >= 2) {
            discounts.add(findLowestPrice(products) / 2);
        }
    }
    //
    //
    // - more Campaigns
    //
    //
    //
    //
    //
    //

    //Finds lowest price in a given product list
    public float findLowestPrice(List<Product> products) {
        Product product = products.stream().min((first, second) ->
                Float.compare(first.pricing(first.getBasePrice()), second.pricing(second.getBasePrice()))).get();
        return product.pricing(product.getBasePrice());
    }

    public float selectCampaign(List<Product> products, List<Item> items) {
        campaignTwoBook(products, items);
        campaignTwoItem(products, items);
        discount = Collections.max(discounts);
        return discount;
    }

}
