package com.paximum.storeproject.service;

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

    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }
}
