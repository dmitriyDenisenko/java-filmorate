package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private int id;
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    private LocalDate birthday;
    @JsonIgnore
    private Set<Integer> friends;

    public User(int id, String email, String login, String name, String birthday) {
        this.id = id;
        this.email = email;
        if (!login.contains(" ")) {
            this.login = login;
        } else {
            throw new ValidationException("Bad login. Have spaces");
        }
        if (name.isBlank()) {
            this.name = login;
        } else {
            this.name = name;
        }
        friends = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthday = LocalDate.parse(birthday, formatter);
        if (this.birthday.isAfter(LocalDate.now())) {
            throw new ValidationException("Date");
        }
        this.friends = new HashSet<>();
    }

    public void addFriend(Integer id) {
        friends.add(id);
    }

    public void removeFriend(Integer id) {
        friends.remove(id);
    }
}
