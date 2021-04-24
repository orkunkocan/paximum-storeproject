package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.MusicAlbum;
import com.paximum.storeproject.repository.MusicAlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MusicAlbumServiceTest { // Tests Service layer while mocking database
    @Autowired
    MusicAlbumService musicAlbumService;
    @MockBean
    MusicAlbumRepository musicAlbumRepository;

    @Test
    public void getMusicAlbumsTest() {
        List<MusicAlbum> musicAlbumList = new ArrayList<>();
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));
        Mockito.when(musicAlbumRepository.getMusicAlbums()).thenReturn(musicAlbumList);
        assertEquals(3, musicAlbumService.getMusicAlbums().size());
    }

    @Test
    public void getMusicAlbumByIdTest() {
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);

        Mockito.when(musicAlbumRepository.findById(1)).thenReturn(java.util.Optional.of(musicAlbum));
        assertEquals(musicAlbum, musicAlbumService.getMusicAlbumById(1));
    }

    @Test
    public void addMusicAlbumTest() {
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);

        Mockito.when(musicAlbumRepository.save(musicAlbum)).thenReturn(musicAlbum);
        assertEquals(musicAlbum, musicAlbumService.addMusicAlbum(musicAlbum));
    }

    @Test
    public void addMusicAlbumsTest() {
        List<MusicAlbum> musicAlbumList = new ArrayList<>();
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));
        musicAlbumList.add(
                new MusicAlbum("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 12));

        Mockito.when(musicAlbumRepository.saveAll(musicAlbumList)).thenReturn(musicAlbumList);
        assertEquals(musicAlbumList, musicAlbumService.addMusicAlbums(musicAlbumList));
    }

    @Test
    public void updateMusicAlbumTest() {
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);

        MusicAlbum updatedMusicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);

        musicAlbum.setProductId(1);
        updatedMusicAlbum.setProductId(1);
        Mockito.when(musicAlbumRepository.findById(musicAlbum.getProductId())).thenReturn(java.util.Optional.of(updatedMusicAlbum));
        musicAlbumService.updateMusicAlbum(musicAlbum);
        verify(musicAlbumRepository, times(1)).findById(1);
    }

    @Test
    public void deleteMusicAlbumTest() {

        musicAlbumService.deleteMusicAlbum(1);
        verify(musicAlbumRepository, times(1)).deleteById(1);
    }
}
