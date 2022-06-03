package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@RestController
public class UserController {
    private int usersId = 1;
    private HashMap<Integer, User> allUsers = new HashMap<>();

    @GetMapping("/users")
    public Collection<User> findAll() {
        log.info("Get request with all users received");
        return allUsers.values();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        if (user.getId() == 0) {
            user.setId(usersId);
            usersId++;
        }
        if (!allUsers.containsKey(user.getId())) {
            allUsers.put(user.getId(), user);
            log.info("User {} added successfully", user.getName());
            return user;
        } else {
            log.info("This User is already on the list");
            throw new ExistingException(user.getName() + " already exists");
        }

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        if (allUsers.containsKey(user.getId())) {
            allUsers.put(user.getId(), user);
            log.info("User {} update successfully", user.getName());
            return user;
        }
        log.info("This User is not already on the list");
        throw new ExistingException(user.getName() + " not exists");
    }
}
