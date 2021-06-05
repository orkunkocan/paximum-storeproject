package com.paximum.storeproject.converter;

import com.paximum.storeproject.dto.BookDto;
import com.paximum.storeproject.dto.FilmDto;
import com.paximum.storeproject.dto.MusicAlbumDto;
import com.paximum.storeproject.dto.ProductDto;
import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.entity.MusicAlbum;
import com.paximum.storeproject.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product product) {
        ModelMapper mapper =new ModelMapper();
        if(product instanceof Book) {
            return mapper.map(product, BookDto.class);
        }else if(product instanceof Film) {
            return mapper.map(product, FilmDto.class);
        }else if(product instanceof MusicAlbum) {
            return mapper.map(product, MusicAlbumDto.class);
        }else {
            return null;
        }
    }

    public List<ProductDto> entityToDto(List<Product> products) {
        return	products.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
