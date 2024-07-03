package com.charles.bookstore.controllers;


import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genres")
    List<Genre> index() {
        return genreRepository.findAll();
    }

    @GetMapping("/genres/{id}")
    Genre show(@PathVariable Long id) {
        return genreRepository.findById(id).get();
    }

    @PostMapping("/genres")
    Genre store(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @PutMapping("/genres/{id}")
    Genre update(@RequestBody Genre newGenre, @PathVariable Long id) {
        var genre = genreRepository.findById(id).get();

        if (!genre.getType().equals(newGenre.getType())) {
            genre.setType(newGenre.getType());
        }

        return genreRepository.save(genre);
    }

    @DeleteMapping("/genres/{id}")
    void delete(@PathVariable Long id) {
        genreRepository.deleteById(id);
    }
}


