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


@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private static String validUser;
    private static String invalidUser;

    @BeforeAll
    static void initializeUsers() {
        validUser = "{\"id\":1,\"name\":\"User Name\",\"login\":\"UserLogin\",\"birthday\":\"2015-03-01\",\"email\":\"email@mail.ru\"}";
        invalidUser = "{\"id\":null,\"name\":\"User Name\",\"login\":\"User Login\",\"birthday\":\"2024-03-01\",\"email\":\"emailmail.ru\"}";
    }

    @SneakyThrows
    @Test
    void createValidUser() {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(validUser))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void getValidUser() {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(validUser))
                .andExpect(status().isOk());
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void createCInvalidUser() {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUser))
                .andExpect(status().isBadRequest());
    }
}
