package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.repository.FilmRepository;
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
public class FilmServiceTest { // Tests Service layer while mocking database
    @Autowired
    FilmService filmService;
    @MockBean
    FilmRepository filmRepository;

    @Test
    public void getFilmsTest() {
        List<Film> filmList = new ArrayList<>();
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));
        Mockito.when(filmRepository.getFilms()).thenReturn(filmList);
        assertEquals(3, filmService.getFilms().size());
    }

    @Test
    public void getFilmByIdTest() {
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);

        Mockito.when(filmRepository.findById(1)).thenReturn(java.util.Optional.of(film));
        assertEquals(film, filmService.getFilmById(1));
    }

    @Test
    public void addFilmTest() {
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);

        Mockito.when(filmRepository.save(film)).thenReturn(film);
        assertEquals(film, filmService.addFilm(film));
    }

    @Test
    public void addFilmsTest() {
        List<Film> filmList = new ArrayList<>();
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));
        filmList.add(
                new Film("productName1", "productGenre1",
                        LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f));

        Mockito.when(filmRepository.saveAll(filmList)).thenReturn(filmList);
        assertEquals(filmList, filmService.addFilms(filmList));
    }

    @Test
    public void updateFilmTest() {
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);

        Film updatedFilm = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);

        film.setProductId(1);
        updatedFilm.setProductId(1);
        Mockito.when(filmRepository.findById(film.getProductId())).thenReturn(java.util.Optional.of(updatedFilm));
        filmService.updateFilm(film);
        verify(filmRepository, times(1)).findById(1);
    }

    @Test
    public void deleteFilmTest() {

        filmService.deleteFilm(1);
        verify(filmRepository, times(1)).deleteById(1);
    }
}
