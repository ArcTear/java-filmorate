package ru.yandex.practicum.filmorate.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FilmController.class)
class FilmControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static String validFilm;
    private static String invalidFilm;

    @BeforeAll
    static void initializeFilms() {
        validFilm = "{\"id\":1,\"name\":\"Film Name\",\"description\":\"Film description\",\"releaseDate\":\"2015-03-01\",\"duration\":100}";
        invalidFilm = "{\"id\":null,\"name\":\"Film Name\",\"description\":\"Film description\",\"releaseDate\":null,\"duration\":100}";
    }

    @SneakyThrows
    @Test
    void createValidFilm() {
        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(validFilm))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void getValidFilm() {
        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(validFilm))
                .andExpect(status().isOk());

        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void createInvalidFilm() {
        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidFilm))
                .andExpect(status().isBadRequest());
    }
}
