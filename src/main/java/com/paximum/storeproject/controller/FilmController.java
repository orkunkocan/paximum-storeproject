package com.paximum.storeproject.controller;

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

    @PostMapping("/addFilm")
    public Film addBook(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @GetMapping("/films")
    public List<Film> getFilms() { return filmService.getFilms(); }

}
