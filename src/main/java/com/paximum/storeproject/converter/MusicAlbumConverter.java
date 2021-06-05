package com.paximum.storeproject.converter;

import com.paximum.storeproject.dto.BookDto;
import com.paximum.storeproject.dto.MusicAlbumDto;
import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.MusicAlbum;
import org.modelmapper.ModelMapper;

public class MusicAlbumConverter {
    public MusicAlbumDto entityToDto(MusicAlbum musicAlbum) {


        ModelMapper mapper =new ModelMapper();
        MusicAlbumDto map = mapper.map(musicAlbum, MusicAlbumDto.class);
        return map;

    }
}
