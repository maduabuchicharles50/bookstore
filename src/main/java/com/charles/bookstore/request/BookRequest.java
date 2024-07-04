package com.charles.bookstore.request;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    @NotBlank
    public String title;
    public Long authorId;
    public Long genreId;
    @NotBlank
    public String isbn;
    @NotBlank
    public String publicationYear;
}
