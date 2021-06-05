package com.paximum.storeproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {

    private int productId;
    private String name;
    private String genre;
}
