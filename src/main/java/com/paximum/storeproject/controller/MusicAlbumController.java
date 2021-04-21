package com.paximum.storeproject.controller;

import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.entity.MusicAlbum;
import com.paximum.storeproject.service.MusicAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MusicAlbumController {

    @Autowired
    private MusicAlbumService musicAlbumService;

    @GetMapping("/musicAlbums")
    public List<MusicAlbum> getMusicAlbum() { return musicAlbumService.getMusicAlbums(); }

    @GetMapping("/musicAlbums/{productId}")
    public MusicAlbum getMusicAlbumByProductId(@PathVariable int productId) {
        return musicAlbumService.getMusicAlbumById(productId);
    }

    @PostMapping("/addMusicAlbum")
    public MusicAlbum addMusicAlbum(@RequestBody MusicAlbum musicAlbum) {
        return musicAlbumService.addMusicAlbum(musicAlbum);
    }

    @PostMapping("/addMusicAlbums")
    public List<MusicAlbum> addMusicAlbums(@RequestBody List<MusicAlbum> musicAlbums) {
        return musicAlbumService.addMusicAlbums(musicAlbums);
    }

    @PostMapping("/updateMusicAlbum")
    public MusicAlbum updateMusicAlbum(@RequestBody MusicAlbum musicAlbum) {
        return musicAlbumService.updateMusicAlbum(musicAlbum);
    }

    @DeleteMapping("/deleteMusicAlbum/{id}")
    public String deleteMusicAlbum(@PathVariable int id) {
        return musicAlbumService.deleteMusicAlbum(id);
    }

}
