package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Film {
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
