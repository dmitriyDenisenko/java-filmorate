package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class Film {
    private int id;
    @NotBlank
    private String name;
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String description;
    private LocalDate releaseDate;
    @Positive
    private int duration;

    public Film(int id, String name, String description, String releaseDate, int duration) throws ValidationException {
        this.id = id;
        this.name = name;
        this.description = description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.releaseDate = LocalDate.parse(releaseDate, formatter);
        if (this.releaseDate.isBefore(LocalDate.parse("1895-12-28", formatter))) {
            throw new ValidationException("Bad date");
        }
        this.duration = duration;
    }

}
