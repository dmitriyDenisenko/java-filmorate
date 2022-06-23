package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.ExistingException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(final ExistingException e) {
        return new ErrorResponse(
                "ExistingException", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse jsonHandle(final HttpMessageNotReadableException e) {
        return new ErrorResponse(
                "Validation Exception", "Wrong information in the request"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse jsonHandle(final ValidationException e) {
        return new ErrorResponse(
                "Validation Exception", "Wrong information in the request"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse parsHandle(final NumberFormatException e) {
        return new ErrorResponse(
                "Path error", "invalid path variable"
        );
    }
}
