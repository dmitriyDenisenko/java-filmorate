package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Collection;

@Slf4j
@RestController
public class FilmController {
    private FilmStorage filmStorage;
    private FilmService filmService;

    @Autowired
    public FilmController(InMemoryFilmStorage inMemoryFilmStorage, FilmService filmService) {
        this.filmStorage = inMemoryFilmStorage;
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public Collection<Film> findAllFilms() {
        return filmStorage.findAllFilms();
    }

    @GetMapping("/films/{id}")
    public Film findFilmById(@PathVariable Integer id) {
        return filmStorage.findFilmById(id);
    }

    @PostMapping("/films")
    public Film addNewFilm(@Validated @RequestBody Film film) {
        return filmStorage.addNewFilm(film);
    }

    @PutMapping("/films")
    public Film updateFilm(@Validated @RequestBody Film film) {
        return filmStorage.updateFilm(film);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.like(userId, id);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.removeLike(userId, id);
    }

    @GetMapping("/films/popular")
    public Collection<Film> findPopularFilms(@RequestParam(required = false, defaultValue = "11") String count) {
        return filmService.findMostLikedMovies(count);
    }
}
