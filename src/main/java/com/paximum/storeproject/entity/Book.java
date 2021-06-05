package com.paximum.storeproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("book")
public class Book extends Product{
    private String ISBN;
    private String writerName;

    public Book() {
    }

    public Book(String name, String genre, LocalDate releaseDate, float basePrice, String ISBN, String writerName) {
        super(name, genre, releaseDate, basePrice);
        this.ISBN = ISBN;
        this.writerName = writerName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    @Override
    public float pricing(float basePrice) {
        return super.pricing(basePrice) + 2.0f;
    }


}
