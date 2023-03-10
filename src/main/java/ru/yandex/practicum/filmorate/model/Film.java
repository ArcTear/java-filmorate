package ru.yandex.practicum.filmorate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Film {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "Введено пустое название фильма.")
    private String name;
    @Size(max = 200, message = "Размер описания привышает 200 символов.")
    private String description;
    @NotNull
    @PastOrPresent(message = "Введена некорректная дата выхода фильма.")
    private LocalDate releaseDate;
    @Positive
    private int duration;
}
