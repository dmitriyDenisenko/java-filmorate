package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class User {
    private int id;
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    private LocalDate birthday;

    public User(int id, String email, String login, String name, String birthday) {
        this.id = id;
        this.email = email;
        if(!login.contains(" ")){
            this.login = login;
        } else {
            throw new ValidationException("Bad login. Have spaces");
        }
        if(name.isBlank()){
            this.name = login;
        } else {
            this.name = name;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthday = LocalDate.parse(birthday,formatter);
        if(this.birthday.isAfter(LocalDate.now())){
            throw new ValidationException("Date");
        }
    }
}
