package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;

@Slf4j
@RestController
public class UserController {
    private UserStorage userStorage;
    private UserService userService;

    @Autowired
    public UserController(InMemoryUserStorage inMemoryUserStorage, UserService userService) {
        this.userStorage = inMemoryUserStorage;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Collection<User> findAll() {
        return userStorage.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userStorage.findUserById(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Collection<User> findMutualFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        return userService.findMutualFriends(id, otherId);
    }

    @GetMapping("/users/{id}/friends")
    public Collection<User> findFriends(@PathVariable Integer id) {
        return userService.findAllFriends(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userStorage.createUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userStorage.updateUser(user);
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.removeFromFriends(id, friendId);
    }
}
