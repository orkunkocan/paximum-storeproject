package com.paximum.storeproject.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "products")
@DiscriminatorColumn(name = "productType", discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue
    private int productId;
    /*
    @Column(name="productType", nullable=false, updatable=false, insertable=false)
    private String productType;
     */
    private String name;
    private String genre;
    private LocalDate releaseDate;
    private float basePrice;

    public Product() {
    }

    public Product(int productId, String name, String genre, LocalDate releaseDate, float basePrice) {
        this.productId = productId;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.basePrice = basePrice;
    }

    public Product(String name, String genre, LocalDate releaseDate, float basePrice) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.basePrice = basePrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getPrice(float basePrice) {
        return basePrice;
    }

    public float pricing2(String type) {
        float basePrice = getBasePrice();
        if(type == "film"){
            return basePrice * 1.15f;
        }
        else if(type == "book"){
            return basePrice + 2.00f;
        }
        else if(type == "music_album"){
            return  basePrice * 0.95f;
        }
        else{
            return basePrice * 1.0f;
        }
    }

    public float pricing(float basePrice) {
        return basePrice * 1.0f;
    }

//    @Transient
//    public String getDiscriminatorValue() {
//        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
//    }



    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", basePrice=" + basePrice +
                '}';
    }

}
