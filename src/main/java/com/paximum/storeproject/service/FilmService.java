package com.paximum.storeproject.service;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getFilms() {
        return filmRepository.getFilms();
    }

    public Film getFilmById(int id) { return filmRepository.findById(id).orElse(null); }

    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }

    public List<Film> addFilms(List<Film> films) { return filmRepository.saveAll(films); }

    public Film updateFilm(Film film) {
        Film updatedFilm = filmRepository.findById(film.getProductId()).orElse(null);
        updatedFilm.setName(film.getName());
        updatedFilm.setGenre(film.getGenre());
        updatedFilm.setReleaseDate(film.getReleaseDate());
        updatedFilm.setBasePrice(film.getBasePrice());
        updatedFilm.setIMDBScore(film.getIMDBScore());
        updatedFilm.setDirectorName(film.getDirectorName());
        return filmRepository.save(updatedFilm);
    }

    public String deleteFilm(int productId) {
        filmRepository.deleteById(productId);
        return "film deleted.";
    }
}
