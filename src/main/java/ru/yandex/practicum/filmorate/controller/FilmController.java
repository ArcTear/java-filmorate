package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.*;

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
            throw new ValidationException();
        }


        film.setId(generateId(film));

        log.debug("Сохранён фильм с id - {}", film.getId());

        films.put(film.getId(), film);

        return film;
    }

    @PutMapping
    Film update(@RequestBody @Valid Film film) throws ValidationException {
        if (isFilmNotValid(film)) {
            throw new ValidationException();
        }
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.debug("Обновлён фильм с id - {}", film.getId());
        } else {
            log.debug("Фильма с id {} нет под контролем", film.getId());
            throw new jakarta.validation.ValidationException();
        }

        return film;
    }
    private int generateId(@Valid Film film){
        int id = film.getId() == 0 ? 1 : film.getId();
        while (films.containsKey(id)) {
            id++;
        }
        return id;
    }

    private boolean isFilmNotValid(@Valid Film film) {
        return !film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28));
    }
}
