package com.paximum.storeproject.converter;

import com.paximum.storeproject.dto.BookDto;
import com.paximum.storeproject.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public BookDto entityToDto(Book book) {


        ModelMapper mapper =new ModelMapper();
        BookDto map = mapper.map(book, BookDto.class);
        return map;

    }
}
