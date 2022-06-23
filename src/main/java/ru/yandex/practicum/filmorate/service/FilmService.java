package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {
    private FilmStorage filmStorage;

    @Autowired
    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.filmStorage = inMemoryFilmStorage;
    }

    public void like(Integer idUser, Integer idFilm) {
        filmStorage.findFilmById(idFilm).addLike(idUser);
        log.info("Film liked");
    }

    public void removeLike(Integer idUser, Integer idFilm) {
        filmStorage.findFilmById(idFilm).removeLike(idUser);
        log.info("Film remove liked");
    }

    public Collection<Film> findMostLikedMovies(String value) {
        int count = Integer.parseInt(value);
        List<Film> films = filmStorage.findAllFilms().stream()
                .sorted(((o1, o2) -> o2.getAmountLikes() - o1.getAmountLikes()))
                .collect(Collectors.toCollection(ArrayList::new));
        log.info("Films found");
        if (films.size() < count) {
            return films;
        }
        return films.subList(0, count);
    }
}
