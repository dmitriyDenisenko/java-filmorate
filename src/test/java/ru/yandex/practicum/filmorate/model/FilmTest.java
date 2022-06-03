package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldBeNotValidateWhenEmptyName() {
        Film film = new Film(1, "", "des", "2022-12-12", 100);
        Set<ConstraintViolation<Film>> validatorSet = validator.validate(film);
        assertFalse(validatorSet.isEmpty());
    }

    @Test
    public void shouldBeNotValidateWhenLongDes() {
        Film film = new Film(1, "name",
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                        "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                        "ddddddddddddddddddddddddddddd", "2022-12-12", 100);
        Set<ConstraintViolation<Film>> validatorSet = validator.validate(film);
        assertFalse(validatorSet.isEmpty());
    }

    @Test
    public void shouldBeNotValidateWhenNotPositiveDuration() {
        Film film = new Film(1, "name", "des", "2022-12-12", -100);
        Set<ConstraintViolation<Film>> validatorSet = validator.validate(film);
        assertFalse(validatorSet.isEmpty());
    }

    @Test
    public void shouldBeValidationExceptionWhenAddLastedDate() {
        ValidationException ex = assertThrows(ValidationException.class, () -> new Film(1, "name",
                "des", "1000-12-12", 100));
        assertEquals("Error when create, error in: Bad date", ex.getMessage());
    }

}