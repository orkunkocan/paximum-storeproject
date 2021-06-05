package com.paximum.storeproject.dto;

import lombok.Data;

@Data
public class BookDto extends ProductDto{

    private String ISBN;
    private String writerName;
}
