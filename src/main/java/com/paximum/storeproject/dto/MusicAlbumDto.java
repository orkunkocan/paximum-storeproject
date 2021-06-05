package com.paximum.storeproject.dto;

import lombok.Data;

@Data
public class MusicAlbumDto extends ProductDto{
    private String singerName;
    private int numberOfSongs;
}
