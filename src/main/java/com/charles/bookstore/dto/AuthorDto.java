package com.charles.bookstore.dto;

import com.charles.bookstore.entity.Author;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "items")
public class AuthorDto {
    public Long id;
    public String name;
    public String contact;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.contact = author.getContact();
    }
}
