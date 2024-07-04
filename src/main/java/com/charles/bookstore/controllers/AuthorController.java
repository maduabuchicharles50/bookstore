package com.charles.bookstore.controllers;

import com.charles.bookstore.dto.AuthorDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    PagedModel<EntityModel<AuthorDto>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"name"}) Pageable paging) {
        return authorService.getAllAuthors(paging);
    }
    @GetMapping("/authors/{id}")
    AuthorDto show(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }
    @PostMapping("/authors")
    AuthorDto store(@RequestBody Author author) {
        return authorService.createAuthors(author);
    }
    @PutMapping("/authors/{id}")
    AuthorDto update(@RequestBody Author newAuthor, @PathVariable Long id) {
        return authorService.updateAuthor(newAuthor,id);
    }
    @DeleteMapping("/authors/{id}")
    void delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}



