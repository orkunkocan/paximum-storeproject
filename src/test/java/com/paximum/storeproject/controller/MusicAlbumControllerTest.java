package com.paximum.storeproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paximum.storeproject.entity.MusicAlbum;
import com.paximum.storeproject.service.MusicAlbumService;
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

@WebMvcTest(MusicAlbumController.class)
public class MusicAlbumControllerTest { //Tests connection layer with MockMvc, Uses Mockito for service

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    private MusicAlbumService musicAlbumService;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getMusicAlbumsTest() throws Exception {
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
        Mockito.when(musicAlbumService.getMusicAlbums()).thenReturn(musicAlbumList);
        String url = "/api/v1/musicAlbums";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(musicAlbumList);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void getMusicAlbumByIdTest() throws Exception {
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);
        Mockito.when(musicAlbumService.getMusicAlbumById(1)).thenReturn(musicAlbum);
        String url = "/api/v1/musicAlbum/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(musicAlbum);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addMusicAlbumTest() throws Exception {
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);
        Mockito.when(musicAlbumService.addMusicAlbum(musicAlbum)).thenReturn(musicAlbum);
        String url = "/api/v1/addMusicAlbum";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(musicAlbum)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(musicAlbum);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void addMusicAlbumsTest() throws Exception {
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
        Mockito.when(musicAlbumService.addMusicAlbums(musicAlbumList)).thenReturn(musicAlbumList);
        String url = "/api/v1/addMusicAlbums";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(musicAlbumList)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(musicAlbumList);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void updateMusicAlbumTest() throws Exception {
        MusicAlbum updatedMusicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);
        MusicAlbum musicAlbum = new MusicAlbum("productName1", "productGenre1",
                LocalDate.of(2020, 1, 1), 10.0f, "testName", 12);

        Mockito.when(musicAlbumService.updateMusicAlbum(musicAlbum)).thenReturn(updatedMusicAlbum);
        String url = "/api/v1/updateMusicAlbum";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(musicAlbum)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getRequest().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(musicAlbum);
        System.out.println("actual: " + actualJsonResponse + ", expected: " + expectedJsonResponse);
        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    public void deleteMusicAlbumTest() throws Exception {
        int id = 1;
        Mockito.when(musicAlbumService.deleteMusicAlbum(id)).thenReturn("musicAlbum deleted");
        String url = "/api/v1/deleteMusicAlbum/" + id;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("result: " + result);
        //Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
    }
}
