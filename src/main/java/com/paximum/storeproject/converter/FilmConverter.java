package com.paximum.storeproject.converter;

import com.paximum.storeproject.dto.BookDto;
import com.paximum.storeproject.dto.FilmDto;
import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Film;
import org.modelmapper.ModelMapper;

public class FilmConverter {
    public FilmDto entityToDto(Film film) {


        ModelMapper mapper =new ModelMapper();
        FilmDto map = mapper.map(film, FilmDto.class);
        return map;

    }
}
