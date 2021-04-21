package com.paximum.storeproject.repository;

import com.paximum.storeproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //@Query("from Product")
    List<Product> findAllByProductIdIn(List<Integer> ids);

    @Query(value = "SELECT product_type from products where product_id = ?1", nativeQuery = true)
    public String findProductTypeByProductId(int productId);
}
