package com.charles.bookstore.controllers;

import com.charles.bookstore.dto.AuthorDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<PagedModel<EntityModel<AuthorDto>>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"name"}) Pageable paging) {
        return new ResponseEntity<>(authorService.getAllAuthors(paging), HttpStatus.OK);    }
    @GetMapping("/authors/{id}")
    ResponseEntity<AuthorDto> show(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }
    @PostMapping("/authors")
    ResponseEntity<AuthorDto> store(@RequestBody @Valid Author author) {
        return new ResponseEntity<>(authorService.createAuthors(author), HttpStatus.CREATED);
    }
    @PutMapping("/authors/{id}")
    ResponseEntity<AuthorDto> update(@RequestBody Author newAuthor, @PathVariable Long id) {
        return new ResponseEntity<>( authorService.updateAuthor(newAuthor,id), HttpStatus.OK);
    }
    @DeleteMapping("/authors/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
       return new ResponseEntity<>( HttpStatus.OK);
    }
}



