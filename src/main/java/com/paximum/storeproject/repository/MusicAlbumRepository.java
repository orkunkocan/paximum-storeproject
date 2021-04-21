package com.paximum.storeproject.repository;

import com.paximum.storeproject.entity.MusicAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicAlbumRepository extends JpaRepository<MusicAlbum, Integer> {

    @Query("from MusicAlbum")
    public List<MusicAlbum> getMusicAlbums();

    //public List<MusicAlbum> findById(int id);
}
