package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.MusicAlbum;
import com.paximum.storeproject.repository.MusicAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicAlbumService {

    @Autowired
    MusicAlbumRepository musicAlbumRepository;

    public List<MusicAlbum> getMusicAlbums() {
        return musicAlbumRepository.getMusicAlbums();
    }
}
