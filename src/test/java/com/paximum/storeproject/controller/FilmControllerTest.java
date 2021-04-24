package com.paximum.storeproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paximum.storeproject.entity.Film;
import com.paximum.storeproject.service.FilmService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(FilmController.class)
public class FilmControllerTest { //Tests connection layer with MockMvc, Uses Mockito for service

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    private FilmService filmService;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getFilmsTest() throws Exception {
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
        Mockito.when(filmService.getFilms()).thenReturn(filmList);
        String url = "/api/v1/films";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(filmList);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void getFilmByIdTest() throws Exception {
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        Mockito.when(filmService.getFilmById(1)).thenReturn(film);
        String url = "/api/v1/film/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(film);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addFilmTest() throws Exception {
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        Mockito.when(filmService.addFilm(film)).thenReturn(film);
        String url = "/api/v1/addFilm";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(film)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(film);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addFilmsTest() throws Exception {
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
        Mockito.when(filmService.addFilms(filmList)).thenReturn(filmList);
        String url = "/api/v1/addFilms";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(filmList)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(filmList);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void updateFilmTest() throws Exception {
        Film updatedFilm = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);
        Film film = new Film("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 10.0f);

        Mockito.when(filmService.updateFilm(film)).thenReturn(updatedFilm);
        String url = "/api/v1/updateFilm";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(film)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(film);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void deleteFilmTest() throws Exception {
        int id = 1;
        Mockito.when(filmService.deleteFilm(id)).thenReturn("film deleted");
        String url = "/api/v1/deleteFilm/" + id;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("result: " + result);
        //Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
    }
}
