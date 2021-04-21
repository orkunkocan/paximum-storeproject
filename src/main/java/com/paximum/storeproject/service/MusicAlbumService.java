package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Film;
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

    public MusicAlbum getMusicAlbumById(int id) { return musicAlbumRepository.findById(id).orElse(null); }

    public MusicAlbum addMusicAlbum(MusicAlbum musicAlbum) {
        return musicAlbumRepository.save(musicAlbum);
    }


    public List<MusicAlbum> addMusicAlbums(List<MusicAlbum> musicAlbums) { return musicAlbumRepository.saveAll(musicAlbums); }

    public MusicAlbum updateMusicAlbum(MusicAlbum musicAlbum) {
        MusicAlbum updatedMusicAlbum = musicAlbumRepository.findById(musicAlbum.getProductId()).orElse(null);
        updatedMusicAlbum.setName(musicAlbum.getName());
        updatedMusicAlbum.setGenre(musicAlbum.getGenre());
        updatedMusicAlbum.setReleaseDate(musicAlbum.getReleaseDate());
        updatedMusicAlbum.setBasePrice(musicAlbum.getBasePrice());
        updatedMusicAlbum.setNumberOfSongs(musicAlbum.getNumberOfSongs());
        updatedMusicAlbum.setSingerName(musicAlbum.getSingerName());
        return musicAlbumRepository.save(updatedMusicAlbum);
    }

    public String deleteMusicAlbum(int productId) {
        musicAlbumRepository.deleteById(productId);
        return "MusicAlbum deleted.";
    }
}
