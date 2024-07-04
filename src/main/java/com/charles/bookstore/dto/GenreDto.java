package com.charles.bookstore.dto;

import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Genre;

public class GenreDto {

    public Long id;
    public String type;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.type = genre.getType();
    }
}
