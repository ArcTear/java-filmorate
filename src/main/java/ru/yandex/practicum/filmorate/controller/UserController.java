package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
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
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        log.debug("Сохранён пользователь: {}", user.getId());

        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    User update(@RequestBody @Valid User user) throws ValidationException {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.debug("Обновлен пользователь с id: {}", user.getId());
        } else {
            log.debug("Пользователя с id {} нет под контролем", user.getId());
            throw new ValidationException();
        }


        users.put(user.getId(), user);

        return user;
    }
}
