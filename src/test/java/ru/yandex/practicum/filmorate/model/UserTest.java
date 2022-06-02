package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreateNormal() throws ValidationException {
        User user = new User(1,"dipster86@yandex.ru", "dim", "dim", "2003-12-04");
        Set<ConstraintViolation<User>> validatorSet = validator.validate(user);
        assertTrue(validatorSet.isEmpty());
    }

    @Test
    public void shouldBeNotValidateWhenEmptyLogin() throws ValidationException {
        User user = new User(1,"dipster86@yandex.ru", "", "dim", "2003-12-04");
        Set<ConstraintViolation<User>> validatorSet = validator.validate(user);
        assertFalse(validatorSet.isEmpty());
    }

    @Test
    public void shouldBeNotValidateWhenHaveSpacesLogin() throws ValidationException{
        ValidationException ex = assertThrows(ValidationException.class, () -> new User(1,
                "dipster86@yandex.ru", "di ma", "dim", "2003-12-04"));
        assertEquals("Error when create, error in: Bad login. Have spaces", ex.getMessage());
    }

    @Test
    public void shouldBeNotValidateWhenFutureDate() throws ValidationException {
        ValidationException ex = assertThrows(ValidationException.class, () -> new User(1,
                "dipster86@yandex.ru", "dima", "dim", "2222-12-04"));
        assertEquals("Error when create, error in: Date", ex.getMessage());
    }

}