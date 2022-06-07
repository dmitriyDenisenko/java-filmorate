package ru.yandex.practicum.filmorate.exceptions;

public class ExistingException extends RuntimeException {
    public ExistingException(String massage) {
        super(massage);
    }
}
