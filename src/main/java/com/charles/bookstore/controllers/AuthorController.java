package com.charles.bookstore.controllers;

import com.charles.bookstore.entity.Author;
import com.charles.bookstore.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
     List<Author> index() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    Author show(@PathVariable Long id) {
        return authorRepository.findById(id).get();
    }

    @PostMapping("/authors")
    Author store(@RequestBody @Valid Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/authors/{id}")
    Author update(@RequestBody Author newAuthor, @PathVariable Long id) {
        var author = authorRepository.findById(id).get();

        if (!author.getName().equals(newAuthor.getName())) {
            author.setName(newAuthor.getName());
        }
        if (!author.getContact().equals(newAuthor.getContact())) {
            author.setContact(newAuthor.getContact());
        }

        return authorRepository.save(author);
    }

    @DeleteMapping("/authors/{id}")
    void delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}



