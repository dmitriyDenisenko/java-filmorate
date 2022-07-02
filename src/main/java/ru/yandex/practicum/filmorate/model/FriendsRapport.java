package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class FriendsRapport {
    int firstUser;
    int secondUser;
    boolean rapport;
}
