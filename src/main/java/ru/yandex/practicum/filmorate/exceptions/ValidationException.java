package ru.yandex.practicum.filmorate.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Error when create, error in: " + message);
    }
}
