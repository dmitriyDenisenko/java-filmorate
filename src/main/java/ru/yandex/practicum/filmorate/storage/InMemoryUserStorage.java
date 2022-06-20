package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private int usersId = 1;
    private HashMap<Integer, User> allUsers = new HashMap<>();

    @Override
    public Collection<User> findAll() {
        log.info("Get request with all users received");
        return allUsers.values();
    }

    @Override
    public User createUser(User user) {
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

    @Override
    public User updateUser(User user) {
        if (allUsers.containsKey(user.getId())) {
            try {
                User oldUser = allUsers.get(usersId);
                user.setFriends(oldUser.getFriends());
            } catch (NullPointerException e) {
                log.info("User hav`t friends");
            } finally {
                allUsers.put(user.getId(), user);
                log.info("User {} update successfully", user.getName());
                return user;
            }
        }
        log.info("This User is not already on the list");
        throw new ExistingException(user.getName() + " not exists");
    }

    @Override
    public User findUserById(Integer id) {
        if (allUsers.containsKey(id)) {
            log.info("User find");
            return allUsers.get(id);
        }
        log.info("User don`t find");
        throw new ExistingException("User does not exist");
    }
}
