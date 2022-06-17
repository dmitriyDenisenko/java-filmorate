package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertThrows;


class FilmControllerTest {
    @Test
    public void shouldNormalAdding() {
        FilmController controller = new FilmController();
        Film film = new Film(0, "Name","Des","2001-12-12", 100);
        controller.addNewFilm(film);
        Assertions.assertEquals("[Film(id=1, name=Name, description=Des, releaseDate=2001-12-12, " +
                "duration=100)]", controller.findAllFilms().toString());
    }
}