package com.paximum.storeproject.dto;

import lombok.Data;

@Data
public class FilmDto extends ProductDto{

    private String directorName;
    private float IMDBScore;
}
