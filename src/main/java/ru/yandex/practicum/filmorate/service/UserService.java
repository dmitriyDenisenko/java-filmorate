package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private UserStorage userStorage;

    @Autowired
    public UserService(InMemoryUserStorage inMemoryUserStorage) {
        this.userStorage = inMemoryUserStorage;
    }

    public Collection<User> findAllFriends(Integer id) {
        User user = userStorage.findUserById(id);
        List<User> users = new ArrayList<>();
        for (Integer userId : user.getFriends()) {
            users.add(userStorage.findUserById(userId));
        }
        log.info("All friends find");
        return users;
    }

    public void addFriend(Integer firstUser, Integer secondUser) {
        userStorage.findUserById(firstUser).addFriend(userStorage.findUserById(secondUser).getId());
        userStorage.findUserById(secondUser).addFriend(userStorage.findUserById(firstUser).getId());
        log.info("Friends adding");
    }

    public void removeFromFriends(Integer firstUser, Integer secondUser) {
        userStorage.findUserById(firstUser).removeFriend(userStorage.findUserById(secondUser).getId());
        userStorage.findUserById(secondUser).removeFriend(userStorage.findUserById(firstUser).getId());
        log.info("Friends removed");
    }

    public Collection<User> findMutualFriends(Integer firstId, Integer secondId) {
        User firstU = userStorage.findUserById(firstId);
        User secondU = userStorage.findUserById(secondId);
        List<Integer> firstUserFriends = new ArrayList<>(firstU.getFriends());
        List<Integer> secondUserFriends = new ArrayList<>(secondU.getFriends());
        firstUserFriends.retainAll(secondUserFriends);
        List<User> users = new ArrayList<>();
        for (int id : firstUserFriends) {
            users.add(userStorage.findUserById(id));
        }
        log.info("Mutual friends find");
        return users;
    }
}
