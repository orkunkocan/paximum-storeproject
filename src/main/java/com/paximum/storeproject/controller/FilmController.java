package com.paximum.storeproject.controller;

import com.paximum.storeproject.entity.Book;
import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/films")
    public List<Film> getFilms() { return filmService.getFilms(); }

    @GetMapping("/film/{productId}")
    public Film getFilmByProductId(@PathVariable int productId) {
        return filmService.getFilmById(productId);
    }

    @PostMapping("/addFilm")
    public Film addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @PostMapping("/addFilms")
    public List<Film> addFilms(@RequestBody List<Film> films) {
        return filmService.addFilms(films);
    }

    @PostMapping("/updateFilm")
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    @DeleteMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable int id) {
        return filmService.deleteFilm(id);
    }



}
