package com.charles.bookstore.dto;

import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Book;
import com.charles.bookstore.entity.Genre;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
@Relation(collectionRelation = "items")
public class BookDto {
    public Long id;
    public String title;
    public Author author;
    public  Genre genre;
    public String isbn;
    public String publicationYear;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
    }
}
