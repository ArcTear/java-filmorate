package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> findAll() {
        log.debug("Кол-во пользователей: {}", users.values().size());

        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) throws ValidationException {
        if (!isUserValid(user)) {
            throw new ValidationException();
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        user.setId(generateId(user));

        log.debug("Сохранён пользователь: {}", user.getId());

        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    User update(@RequestBody @Valid User user) throws ValidationException {
        if (!isUserValid(user)) {
            throw new ValidationException();
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.debug("Обновлен пользователь с id: {}", user.getId());
        } else {
            log.debug("Пользователя с id {} нет под контролем", user.getId());
            throw new jakarta.validation.ValidationException();
        }


        users.put(user.getId(), user);

        return user;
    }

    private int generateId(@Valid User user) {
        int id = user.getId() == 0 ? 1 : user.getId();
        while (users.containsKey(id)) {
            id++;
        }
        return id;
    }

    private boolean isUserValid(@Valid User user) {
        return !user.getLogin().contains(" ");
    }
}
