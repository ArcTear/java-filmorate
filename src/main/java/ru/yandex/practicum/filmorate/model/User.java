package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Email(message = "Введен некорректный Email.")
    private String email;
    @NotBlank (message = "Введен пустой логин.")
    private String login;
    private String name;
    @PastOrPresent(message = "Дата рождения пользователя не может быть в будущем.")
    private LocalDate birthday;
}
