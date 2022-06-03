package ru.yandex.practicum.filmorate.exceptions;

public class ExistingException extends Error{
    public ExistingException(String massage){
        super(massage);
    }
}
