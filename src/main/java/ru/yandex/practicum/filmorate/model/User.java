package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Data
public class User {
    private int id;
    @Email(message = "Введен некорректный Email.")
    private String email;
    @NotBlank(message = "Введен пустой логин.")
    private String login;
    private String name;
    @PastOrPresent(message = "Дата рождения пользователя не может быть в будущем.")
    private LocalDate birthday;
}
