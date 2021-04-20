package com.paximum.storeproject.repository;

import com.paximum.storeproject.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("from Film")
    public List<Film> getFilms();

    public List<Film> findById(int id);
}
