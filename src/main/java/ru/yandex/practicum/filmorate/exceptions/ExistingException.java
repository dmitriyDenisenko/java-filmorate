package ru.yandex.practicum.filmorate.exceptions;

public class ExistingException extends Exception{
    public ExistingException(){

    };
    public ExistingException(String massage){
        super(massage);
    }
}
