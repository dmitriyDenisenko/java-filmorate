package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Collection<Film> findAllFilms();

    Film addNewFilm(Film film);

    Film updateFilm(Film film);

    Film findFilmById(Integer id);

}
