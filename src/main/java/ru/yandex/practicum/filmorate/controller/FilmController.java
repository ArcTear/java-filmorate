package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> findAll() {
        log.debug("Кол-во фильмов: {}", films.values().size());

        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film create(@RequestBody @Valid Film film) throws ValidationException {
        if (isFilmNotValid(film)) {
            log.error("Дата выхода фильма некорректна.");
            throw new ValidationException();
        }

        log.debug("Сохранён фильм с id - {}", film.getId());

        films.put(film.getId(), film);

        return film;
    }

    @PutMapping
    Film update(@RequestBody @Valid Film film) throws ValidationException {
        if (isFilmNotValid(film)) {
            log.error("Дата выхода фильма некорректна.");
            throw new ValidationException();
        }
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.debug("Обновлён фильм с id - {}", film.getId());
        } else {
            log.error("Фильма с id {} нет под контролем", film.getId());
            throw new ValidationException();
        }

        return film;
    }

    private boolean isFilmNotValid(@Valid Film film) {
        return !film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28));
    }
}
