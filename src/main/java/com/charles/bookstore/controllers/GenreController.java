package com.charles.bookstore.controllers;


import com.charles.bookstore.dto.GenreDto;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.repository.GenreRepository;
import com.charles.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    PagedModel<EntityModel<GenreDto>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"type"}) Pageable paging) {
        return genreService.getAllGenres(paging);
    }

    @GetMapping("/genres/{id}")
    GenreDto show(@PathVariable Long id) {
        return genreService.getGenre(id);
    }
    @PostMapping("/genres")
    GenreDto store(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }
    @PutMapping("/genres/{id}")
    GenreDto update(@RequestBody Genre newGenre, @PathVariable Long id) {
      return genreService.updateGenre(newGenre,id);
    }
    @DeleteMapping("/genres/{id}")
    void delete(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}


