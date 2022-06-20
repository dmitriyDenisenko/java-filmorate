package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private int filmsId = 1;
    private Map<Integer, Film> allFilms = new HashMap<>();

    @Override
    public Collection<Film> findAllFilms() {
        log.info("Get request with all movies received");
        return allFilms.values();
    }

    @Override
    public Film addNewFilm(Film film) {
        if (film.getId() == 0) {
            film.setId(filmsId);
            filmsId++;
        }
        if (!allFilms.containsKey(film.getId())) {
            allFilms.put(film.getId(), film);
            log.info("Movie {} added successfully", film.getName());
            return film;
        } else {
            log.info("This movie is already on the list");
            throw new ExistingException(film.getName() + " already exists");
        }
    }

    @Override
    public Film updateFilm(Film film) {
        if (allFilms.containsKey(film.getId())) {
            allFilms.put(film.getId(), film);
            log.info("Movie {} update successfully", film.getName());
            return film;
        }
        log.info("This movie is not already on the list");
        throw new ExistingException(film.getName() + " not exists");
    }

    @Override
    public Film findFilmById(Integer id) {
        if (allFilms.containsKey(id)) {
            log.info("Film {} find", id);
            return allFilms.get(id);
        }
        throw new ExistingException("User does not exist");
    }
}
