package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@RestController
public class FilmController {
    private int filmsId = 1;
    private HashMap<Integer, Film> allFilms = new HashMap<>();

    @GetMapping("/films")
    public Collection<Film> findAllFilms() {
        log.info("Get request with all movies received");
        return allFilms.values();
    }

    @PostMapping("/films")
    public Film addNewFilm(@Validated @RequestBody Film film) throws ExistingException {
        if(film.getId() == 0){
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

    @PutMapping("/films")
    public Film updateFilm(@Validated @RequestBody Film film) throws ExistingException {
        if (allFilms.containsKey(film.getId())) {
            allFilms.put(film.getId(), film);
            log.info("Movie {} update successfully", film.getName());
            return film;
        }
        log.info("This movie is not already on the list");
        throw new ExistingException(film.getName() + " not exists");


    }
}
