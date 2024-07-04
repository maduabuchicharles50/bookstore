package com.charles.bookstore.dto;

import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Genre;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class BookDto {

    public Long authorId;
    public Long genreId;
    public String isbn;
    public String publicationYear;

}
